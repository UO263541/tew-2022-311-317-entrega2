package com.tew.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.model.PisoParaVisitar;

@ManagedBean(name="pisoPV")
@SessionScoped
public class BeanPisoParaVisitar extends PisoParaVisitar implements Serializable {
	private static final long serialVersionUID = 66L;
	
	public BeanPisoParaVisitar() {
		iniciaPisoParaVisitar(null);
	}
//Este método es necesario para copiar el alumno a editar cuando
//se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse 
//por un método editar en BeanAlumnos.
	public void setPisoParaVisitar(PisoParaVisitar pisoPV) {
		setIdPiso(pisoPV.getIdPiso());
		setIdCliente(pisoPV.getIdCliente());
		setFechaHoraCita(pisoPV.getFechaHoraCita());
		this.setEstadoVisita(pisoPV.getEstadoVisita());
	}
//Iniciamos los datos del alumno con los valores por defecto 
//extraídos del archivo de propiedades correspondiente
    public void iniciaPisoParaVisitar(ActionEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    ResourceBundle bundle = 
 	        facesContext.getApplication().getResourceBundle(facesContext, "msgs");
    	    this.setIdPiso(null);
    	    this.setIdCliente(new Long(4));
    	    this.setFechaHoraCita(new Long(4));
    	    this.setEstadoVisita(1);
	  }	      
}
