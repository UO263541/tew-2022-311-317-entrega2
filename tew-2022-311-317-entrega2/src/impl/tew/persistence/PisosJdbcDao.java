package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.Piso;
import com.tew.persistence.*;
import com.tew.persistence.exception.*;


/**
 * Implementaci��n de la interfaz de fachada al servicio de persistencia para
 * Alumnos. En este caso es Jdbc pero podr��a ser cualquier otra tecnologia 
 * de persistencia, por ejemplo, la que veremos m��s adelante JPA 
 * (mapeador de objetos a relacional)
 * 
 * @author Enrique
 *
 */
public class PisosJdbcDao implements PisosDao {

	public List<Piso> getPisos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Piso> pisos = new ArrayList<Piso>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.PISOS");
			rs = ps.executeQuery();

			while (rs.next()) {
				Piso piso = new Piso();
				piso.setId(rs.getLong("ID"));
				piso.setIdAgente(rs.getLong("ID_AGENTE"));
				piso.setPrecio(rs.getDouble("PRECIO"));
				piso.setDireccion(rs.getString("DIRECCION"));
				piso.setCiudad(rs.getString("CIUDAD"));
				piso.setAnio(rs.getInt("ANIO"));
				piso.setEstado(rs.getInt("ESTADO"));
				piso.setFoto("FOTO");
				pisos.add(piso);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return pisos;
	}

	@Override
	public void deletePiso(Long id) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from PUBLIC.PISOS where id = ?");
			
			ps.setLong(1, id);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Piso " + id + " not found");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}

	@Override
	public Piso findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Piso piso = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.PISOS where id = ?");
			ps.setLong(1, id); //Rompe aqui y no le encuentro sentido ninguno
			
			
			rs = ps.executeQuery();
			if (rs.next()) {
				piso = new Piso();
				piso.setId(rs.getLong("ID"));
				piso.setIdAgente(rs.getLong("ID_AGENTE"));
				piso.setPrecio(rs.getDouble("PRECIO"));
				piso.setDireccion(rs.getString("DIRECCION"));
				piso.setCiudad(rs.getString("CIUDAD"));
				piso.setAnio(rs.getInt("ANIO"));
				piso.setEstado(rs.getInt("ESTADO"));
				piso.setFoto(rs.getString("FOTO"));

				
				
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return piso;
	}

	@Override
	public void savePiso(Piso piso) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into PUBLIC.PISOS (id_agente, precio, direccion, ciudad, anio, estado, foto) " + 
					"values ( ?, ?, ?, ?, ?, ?, ?)");
			
			ps.setLong(1, piso.getIdAgente());
			ps.setDouble(2, piso.getPrecio());
			ps.setString(3, piso.getDireccion());
			ps.setString(4, piso.getCiudad());
			ps.setInt(5, piso.getAnio());
			ps.setInt(6, piso.getEstado());
			ps.setString(7,piso.getFoto());
			

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Piso " + piso + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}

	@Override
	public void updatePiso(Piso piso) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"UPDATE PISOS SET ID_AGENTE=?, PRECIO=?, DIRECCION=?, CIUDAD=?, ANIO=?, ESTADO=?, FOTO=? WHERE ID=?");


			ps.setLong(1, piso.getIdAgente());
			ps.setDouble(2, piso.getPrecio());
			ps.setString(3, piso.getDireccion());
			ps.setString(4, piso.getCiudad());
			ps.setInt(5, piso.getAnio());
			ps.setInt(6, piso.getEstado());
			ps.setString(7,piso.getFoto());
			ps.setLong(8, piso.getId());


			
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Piso " + piso + " not found");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}

}
