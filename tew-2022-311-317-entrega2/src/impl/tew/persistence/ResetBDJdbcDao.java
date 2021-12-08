package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.Piso;
import com.tew.persistence.*;
import com.tew.persistence.exception.*;


/**
 * Implementaciï¿½ï¿½n de la interfaz de fachada al servicio de persistencia para
 * Alumnos. En este caso es Jdbc pero podrï¿½ï¿½a ser cualquier otra tecnologia 
 * de persistencia, por ejemplo, la que veremos mï¿½ï¿½s adelante JPA 
 * (mapeador de objetos a relacional)
 * 
 * @author Enrique
 *
 */
public class ResetBDJdbcDao implements ResetBDDao {

	
	

	@Override
	public void resetbd() throws Exception {
		// TODO Auto-generated method stub
		
		//Eliminamos los datos de las tablas deseadas
		ejecutaSQL("delete from Agentes");
		ejecutaSQL("delete from Pisos");
		
		//Añadimos los agentes deseados
		ejecutaSQL("INSERT INTO AGENTES VALUES(1,'agente1@micorreo.com','clave1')");
		ejecutaSQL("INSERT INTO AGENTES VALUES(2,'agente2@micorreo.com','clave2')");
		
	}
	
	public void ejecutaSQL(String s) {
		PreparedStatement ps = null;
		Connection con = null;
		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexion a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(s);

			ps.executeUpdate();
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
