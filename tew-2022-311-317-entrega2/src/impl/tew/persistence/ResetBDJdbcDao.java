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
public class ResetBDJdbcDao implements ResetBDDao {

	
	

	@SuppressWarnings("resource")
	@Override
	public void resetbd() throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Connection con = null;



		try {



			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";



			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");



			ps = con.prepareStatement("DELETE FROM PISOS");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM AGENTES");
			ps.executeUpdate();



			ps = con.prepareStatement("INSERT INTO AGENTES VALUES(1,'agente1','clave1')");
			ps.executeUpdate();
			ps = con.prepareStatement("INSERT INTO AGENTES VALUES(2,'agente2','clave2')");
			ps.executeUpdate();



		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}
}
