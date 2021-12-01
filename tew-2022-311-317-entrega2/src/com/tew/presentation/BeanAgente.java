package com.tew.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.model.Agente;

@ManagedBean(name="agente")
@SessionScoped
public class BeanAgente extends Agente implements Serializable {
	private static final long serialVersionUID = 44L;
	
	public BeanAgente() {
		iniciaAgente(null);
	}
//Este método es necesario para copiar el alumno a editar cuando
//se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse 
//por un método editar en BeanAlumnos.
	public void setAgente(Agente agente) {
		setId(agente.getId());
		setLogin(agente.getLogin());
		setPasswd(agente.getPasswd());
		
	}
//Iniciamos los datos del alumno con los valores por defecto 
//extraídos del archivo de propiedades correspondiente
    public void iniciaAgente(ActionEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    ResourceBundle bundle = 
 	        facesContext.getApplication().getResourceBundle(facesContext, "msgs");
    	    setId(null);
    	    setLogin(bundle.getString("LoginAgentePorDefecto"));
    		setPasswd(bundle.getString("PasswdAgentePorDefecto"));
	  }	      
}
