package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.PisoParaVisitar;
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
public class PisosParaVisitarJdbcDao implements PisosParaVisitarDao {


	

	
	@Override
	public void updatePisoParaVisitar(PisoParaVisitar pisopv) throws NotPersistedException {
		// TODO Auto-generated method stub
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
					"update Pisos_para_visitar " +
							"set fecha_hora_cita = ?, estado_visita=? " +
							"where id_piso = ? and id_cliente=?");
					
				
			ps.setLong(1, pisopv.getFechaHoraCita());
			ps.setInt(2, pisopv.getEstadoVisita());
			ps.setLong(3, pisopv.getIdPiso());
			ps.setLong(4, pisopv.getIdCliente());

		
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Piso Para Visitar: " + pisopv + " not found");
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
	public PisoParaVisitar findByIds(Long idPiso, Long idCliente) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		PisoParaVisitar pisopv = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.PISOS_PARA_VISITAR where id_piso = ? and id_cliente=?");
			ps.setLong(1, idPiso);
			ps.setLong(2, idCliente);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				pisopv = new PisoParaVisitar();
				pisopv.setIdPiso(rs.getLong("ID_PISO"));
				pisopv.setIdCliente(rs.getLong("ID_CLIENTE"));
				pisopv.setFechaHoraCita(rs.getLong("FECHA_HORA_CITA"));
				pisopv.setEstadoVisita(rs.getInt("ESTADO_VISITA"));
				
				
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
		
		return pisopv;
	}

	@Override
	public void savePisoParaVisitar(PisoParaVisitar pisopv) throws AlreadyPersistedException {
		// TODO Auto-generated method stub
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
			"insert into PUBLIC.PISOS_PARA_VISITAR (id_piso, id_cliente, fecha_hora_cita, estado_visita) " + 
			"values ( ?, ?, ?, ?)");
	
	
			ps.setLong(1, pisopv.getIdPiso());
			ps.setLong(2, pisopv.getIdCliente());
			ps.setLong(3, pisopv.getFechaHoraCita());
			ps.setInt(4, 1);
			


			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Piso Para Visitar " + pisopv + " already persisted");
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
	public void deletePisoParaVisitar(Long idPiso, Long idCliente) throws NotPersistedException {
		// TODO Auto-generated method stub
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
			ps = con.prepareStatement("delete from PUBLIC.PISOS_PARA_VISITAR where id = ?");
			
			ps.setLong(1, idPiso);
			ps.setLong(1, idCliente);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Piso Para Visitar: " + idPiso + " not found");
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
	public List<PisoParaVisitar> getPisosParaVisitar() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<PisoParaVisitar> pisos = new ArrayList<PisoParaVisitar>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.PISOS_PARA_VISITAR");
			rs = ps.executeQuery();

			while (rs.next()) {
				PisoParaVisitar pisopv = new PisoParaVisitar();
				pisopv.setIdPiso(rs.getLong("ID_PISO"));
				pisopv.setIdCliente(rs.getLong("ID_CLIENTE"));
				pisopv.setFechaHoraCita(rs.getLong("FECHA_HORA_CITA"));
				pisopv.setEstadoVisita(rs.getInt("ESTADO_VISITA"));
				pisos.add(pisopv);
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



}
