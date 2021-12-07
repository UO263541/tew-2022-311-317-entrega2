package com.tew.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


@ManagedBean
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	// uso de inyecciÃ³n de dependencia
	@ManagedProperty(value = "#{piso}")
	private BeanPiso piso;
	
	@ManagedProperty(value = "#{cliente}")
	private BeanCliente cliente;
	
	@ManagedProperty(value = "#{pisoPV}")
	private BeanPisoParaVisitar pisoVisita;

	public BeanPiso getPiso() {
		return piso;
	}

	public void setPiso(BeanPiso piso) {
		this.piso = piso;
	}

	
	public BeanCliente getCliente() {
		return cliente;
	}
	
	public void setCliente(BeanCliente cliente) {
		this.cliente = cliente;
	}
	
	
	public BeanPisoParaVisitar getPisoVisitar() {
		return pisoVisita;
	}
	
	public void setPisoVisitar(BeanPisoParaVisitar pisoVisita) {
		this.pisoVisita = pisoVisita;
	}
	
	public Locale getLocale() {
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (piso != null) {
				if (piso.getId()==null) //valores por defecto del piso, si no NO inicializar
					piso.iniciaPiso(null);
			}
			if (cliente != null) {
				if (cliente.getId()==null) //valores por defecto del cliente, si no NO inicializar
					cliente.iniciaCliente(null);
			}
			if (pisoVisita != null) {
				if (pisoVisita.getIdPiso()==null) //valores por defecto del piso a visitar, si no NO inicializar
					pisoVisita.iniciaPisoParaVisitar(null);
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (piso != null) {
				if (piso.getId()==null) //valores por defecto del piso, si no NO inicializar
					piso.iniciaPiso(null);
			}
			if (cliente != null) {
				if (cliente.getId()==null) //valores por defecto del cliente, si no NO inicializar
					cliente.iniciaCliente(null);
			}
			if (pisoVisita != null) {
				if (pisoVisita.getIdPiso()==null) //valores por defecto del piso a visitar, si no NO inicializar
					pisoVisita.iniciaPisoParaVisitar(null);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");
		piso = (BeanPiso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("piso"));
		
		cliente = (BeanCliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("cliente"));
		
		pisoVisita = (BeanPisoParaVisitar) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("pisoPV"));

		if (piso == null) {
			System.out.println("BeanSettings - No existia el piso");
			piso = new BeanPiso();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("piso", piso);
			System.out.println(piso);
		}
		if (cliente == null) {
			System.out.println("BeanSettings - No existia el cliente");
			cliente = new BeanCliente();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("cliente", cliente);
		}
		if (pisoVisita == null) {
			System.out.println("BeanSettings - No existia el piso para visitar");
			pisoVisita = new BeanPisoParaVisitar();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("pisoVisita", pisoVisita);
		}
		
	}


	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

}
