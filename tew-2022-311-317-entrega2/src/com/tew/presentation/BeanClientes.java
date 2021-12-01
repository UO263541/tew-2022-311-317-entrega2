package com.tew.presentation;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.ClientesService;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.model.Piso;
import com.tew.model.User;

@ManagedBean(name="cliente")
@SessionScoped
public class BeanClientes extends Cliente implements Serializable {
	private static final long serialVersionUID = 45L;
	
	private Cliente[] clientes = null;
    
	@ManagedProperty(value="#{cliente}") 
	private BeanCliente cliente;
	public BeanCliente getCliente() { return cliente; }
	public void setCliente(BeanCliente cliente) {this.cliente = cliente;}

	public Cliente[] getClientes() {
		return(clientes);
	}

	public void setClientes(Cliente[] clientes) {
		this.clientes = clientes;
	}  
	
	public void iniciaCliente(ActionEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ResourceBundle bundle = 
	        facesContext.getApplication().getResourceBundle(facesContext, "msgs");
	    cliente.setId(null);
	    cliente.setLogin(bundle.getString("loginClienteDefecto"));
	    cliente.setPasswd(bundle.getString("passwdClienteDefecto"));
	    cliente.setNombre(bundle.getString("nombreClienteDefecto"));
	    cliente.setApellidos(bundle.getString("apellidosClienteDefecto"));
	    cliente.setEmail(bundle.getString("emailClienteDefecto"));
	}
	
	
	public String listado() {
		ClientesService service;
		try {
			service = Factories.services.createClientesService();
			clientes = (Cliente [])service.getClientes().toArray(new Cliente[0]);

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}
	
	public String baja(Piso piso) {
		ClientesService service;
		try {
			service = Factories.services.createClientesService();
			service.deleteCliente(cliente.getId());
			clientes = (Cliente [])service.getClientes().toArray(new Cliente[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}
	
	
	public String edit() {
		ClientesService service;
		try {
			service = Factories.services.createClientesService();
			cliente = (BeanCliente) service.findById(cliente.getId());
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();  
			return "error";
		}

	}
	
	public String salva() {
		ClientesService service;
		Map<String, Object> sesiones = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		User usuario = (User) sesiones.get("LOGGEDIN_USER");
		if(usuario == null) {
			User nuevouser = new User(cliente.getLogin(), cliente.getNombre());
			sesiones.put("LOGGEDIN_USER", nuevouser);
		}
		try {
			service = Factories.services.createClientesService();
			if (cliente.getId()==null) {
				
				service.saveCliente(cliente);
			}
			else {
				service.updateCliente(cliente);
			} 
			clientes = (Cliente [])service.getClientes().toArray(new Cliente[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	
	@PostConstruct
	public void init() {    	  
		System.out.println("BeanClientes - Eejeecución PostConstruct"); 
		cliente = (BeanCliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("cliente"));
		if (clientes == null) { 
			System.out.println("BeanClientes - No existia");
			cliente = new BeanCliente();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "cliente",cliente);
		}
	}
	@PreDestroy
	public void end()  {
		System.out.println("BeanClientes - Eejecución PreDestroy");
	}
}
