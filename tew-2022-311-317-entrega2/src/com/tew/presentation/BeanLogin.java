package com.tew.presentation;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.User;
import com.tew.persistence.exception.PersistenceException;

@ManagedBean(name="login")
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 8765;
	private String name = "";
	private String password = "";
	
	private User user = null;

	
	public String verify() {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;
		LoginService login = Factories.services.createLoginService();
		user = login.verify(name, password);
		if (user != null) {
			putUserInSession(user);
			if(user.getName()=="UsuarioAgente") {
				System.out.println("Soy un agente");
				return "success_agente";
			}else {
				return "success";
			}
		}
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("login_form_result_error"), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "login";
	}
	private void putUserInSession(User user) {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String logout() {
		if(user != null) {
			Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			session.remove("LOGGEDOUT_USER", user);
			return "exito";
		}
		return "error";
	}
	
	public void exeSQL(String s) {
		PreparedStatement ps = null;
		Connection con = null;
		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexión a la base de datos.
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



  //Metodo para resetear la BD
  	public String resetBaseDatos() {
  		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
  		session.clear();

  		try {
  		//exeSQL("delete from PUBLIC.AGENTES");
  		//exeSQL("delete from PUBLIC.CLIENTES");
  			exeSQL("delete from pisos");
  		//exeSQL("delete from pisos_para_Visitar");
  		/*exeSQL("INSERT INTO AGENTES VALUES(1,'agente1@micorreo.com','clave1')");
  		exeSQL("INSERT INTO AGENTES VALUES(2,'agente2@micorreo.com','clave2')");
  		exeSQL("INSERT INTO CLIENTES VALUES(1,'user1@micorreo.com','clave1','CLIENTEA','APELLIDOA1 APELLIDOA2','user1@micorreo.com')");
  		exeSQL("INSERT INTO CLIENTES VALUES(2,'user2@micorreo.com','clave2','CLIENTEB','APELLIDOB1 APELLIDOB2','user2@micorreo.com')");
  		*/
  		} catch (Exception e) {
  			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
  		}

  		return "exito";
  				
  	}
	

}
