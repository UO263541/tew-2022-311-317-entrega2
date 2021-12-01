package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.Agente;
import com.tew.persistence.AgentesDao;
import com.tew.persistence.ClientesDao;
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
public class AgentesJdbcDao implements AgentesDao {

	public List<Agente> getAgentes() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Agente> agentes = new ArrayList<Agente>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.AGENTES");
			rs = ps.executeQuery();

			while (rs.next()) {
				Agente agente = new Agente();
				agente.setId(rs.getLong("ID"));
				agente.setLogin(rs.getString("LOGIN"));
				agente.setPasswd(rs.getString("PASSWD"));

				agentes.add(agente);
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
		
		return agentes;
	}

	@Override
	public void delete(Long id) throws NotPersistedException {
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
			ps = con.prepareStatement("delete from PUBLIC.AGENTES where id = ?");
			
			ps.setLong(1, id);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Agente " + id + " not found");
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
	public Agente findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Agente agente = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.AGENTES where id = ?");
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				agente = new Agente();
				agente.setId(rs.getLong("ID"));
				agente.setLogin(rs.getString("LOGIN"));
				agente.setPasswd(rs.getString("PASSWD"));

				
				
				
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
		
		return agente;
	}

	@Override
	public void save(Agente agente) throws AlreadyPersistedException {
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
					"insert into PUBLIC.AGENTES (login, passwd) " +
					"values (?, ?)");
			
			ps.setString(1, agente.getLogin());
			ps.setString(2, agente.getPasswd());
			

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Agente " + agente + " already persisted");
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
	public void update(Agente agente) throws NotPersistedException {
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
					"update PUBLIC.AGENTES " +
					"set login = ?, passwd = ?" +
					"where id = ?");
			
			ps.setString(1, agente.getLogin());
			ps.setString(2, agente.getPasswd());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Agente " + agente + " not found");
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
	public void reset() throws Exception {
		// TODO Auto-generated method stub
				PreparedStatement tablaAgentes = null;
				PreparedStatement tablaClientes = null; 
				PreparedStatement tablaPisosParaVisitar = null;
				PreparedStatement tablaPisos = null;
				Connection con = null;
				
				try {
					String SQL_DRV = "org.hsqldb.jdbcDriver";
					String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
					Class.forName(SQL_DRV);
					con = DriverManager.getConnection(SQL_URL, "sa", "");
					tablaAgentes = con.prepareStatement("delete from PUBLIC.AGENTES");
					tablaClientes = con.prepareStatement("delete from PUBLIC.CLIENTES");
					tablaPisosParaVisitar = con.prepareStatement("delete from PUBLIC.PISOS_PARA_VISITAR");
					tablaPisos = con.prepareStatement("delete from PUBLIC.PISOS");
					
					tablaAgentes.executeUpdate();
					tablaClientes.executeUpdate();
					tablaPisosParaVisitar.executeUpdate();
					tablaPisos.executeUpdate();
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw new PersistenceException("Driver not found", e);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new PersistenceException("Invalid SQL or database schema", e);
				}
				finally  {
					if (tablaAgentes != null) {try{ tablaAgentes.close(); } catch (Exception ex){}};
					if (tablaClientes != null) {try{ tablaClientes.close(); } catch (Exception ex){}};
					if (tablaPisosParaVisitar != null) {try{ tablaPisosParaVisitar.close(); } catch (Exception ex){}};
					if (tablaPisos != null) {try{ tablaPisos.close(); } catch (Exception ex){}};
					if (con != null) {try{ con.close(); } catch (Exception ex){}};
				}
	}

}
