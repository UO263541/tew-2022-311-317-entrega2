package com.tew.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.model.Agente;
import com.tew.model.Cliente;

@ManagedBean(name="cliente")
@SessionScoped
public class BeanCliente extends Cliente implements Serializable {
	private static final long serialVersionUID = 44L;
	
	public BeanCliente() {
		iniciaCliente(null);
	}
//Este método es necesario para copiar el alumno a editar cuando
//se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse 
//por un método editar en BeanClientes.
	public void setCliente(Cliente cliente) {
		setId(cliente.getId());
		setLogin(cliente.getLogin());
		setPasswd(cliente.getPasswd());
		setRep_passwd(cliente.getRep_passwd());
		setEmail(cliente.getEmail());
		setNombre(cliente.getNombre());
		setApellidos(cliente.getApellidos());
	}
//Iniciamos los datos del cliente con los valores por defecto 
//extraídos del archivo de propiedades correspondiente
    public void iniciaCliente(ActionEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    ResourceBundle bundle = 
 	        facesContext.getApplication().getResourceBundle(facesContext, "msgs");
    	    setId(null);
    	    setLogin(bundle.getString("cliente"));
    		setPasswd(bundle.getString("password"));
    		setRep_passwd(bundle.getString("password"));
    		setEmail(bundle.getString("email"));
    		setNombre(bundle.getString("nombre"));
    		setApellidos(bundle.getString("apellidos"));
	  }	      
}
