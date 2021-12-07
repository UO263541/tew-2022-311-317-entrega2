package com.tew.presentation;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.AgentesService;
import com.tew.business.PisosParaVisitarService;
import com.tew.business.PisosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Piso;
import com.tew.model.PisoParaVisitar;
import com.tew.model.User;

@ManagedBean
@SessionScoped
public class BeanPisos implements Serializable{
	    private static final long serialVersionUID = 55555L;
		
        private Piso[] pisos= null;
        private Piso[] pisosFiltrados = null;
        private String ciudad = null;
        double precioMinimo=0.0;
    	double precioMaximo=0.0;
   
        
        //Uso de inyección de dependencia
        @ManagedProperty(value="#{piso}") 
    	private BeanPiso piso;
    	public String getCiudad() {
    		return ciudad;
    	}
    	

    	public void setCiudad(String ciudad) {
    		this.ciudad = ciudad;
    	}
    	public BeanPiso getPiso() { 
    		return piso; 
    	}
    	public void setPiso(BeanPiso piso) {
    		this.piso = piso;
    	}
    	
    	
    	public Piso[] getPisos() {
    		return pisos;
    	}
    	
    	public void setPisos(Piso[] pisos) {
    		this.pisos = pisos;
    	}
    	
    	public double getPrecioMinimo() { 
    		return precioMinimo; 
    	}
    	
    	public void setPrecioMinimo(double precioMinimo) { 
    		this.precioMinimo = precioMinimo; 
    	}
    	
    	public double getPrecioMaximo() { 
    		return precioMaximo;
    	}
    	
    	public void setPrecioMaximo(double precioMaximo) {
    		this.precioMaximo = precioMaximo;
    	}
    	
    	
    	//Función inicia piso
    	public void iniciaPisos(ActionEvent event) {
    		FacesContext facesContext = FacesContext.getCurrentInstance();
    		//Obtenemos el archivo de propiedades correspondiente al idioma que
    		//tengamos seleccionado y que viene envuelto en facesContext
    		ResourceBundle bundle = 
    				facesContext.getApplication().getResourceBundle(facesContext, "msgs");
    		piso.setId(null);
    		piso.setIdAgente(null);
    		piso.setPrecio(0.0);
    		piso.setDireccion(bundle.getString("DireccionDefecto"));
    		piso.setCiudad(bundle.getString("CiudadDefecto"));
    		piso.setAnio(0);
    		piso.setEstado(1);
    	}
    	

    	public String listado() {
    		PisosService service;
    		try {
    			service = Factories.services.createPisosService();
    			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
    			return "exito";
    		} catch (Exception e) {
    			e.printStackTrace();  
    			return "error";
    		}
    	}
    	
    	public String listado2() {
    		PisosService service;
    		try {
    			service = Factories.services.createPisosService();
    			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
    			return "exito";
    		} catch (Exception e) {
    			e.printStackTrace();  
    			return "error";
    		}
    	}
    	
    	
    	//obtener el id del agente para mostrarle sus pisos
    	public Long getIdAgente() {
            AgentesService service=Factories.services.createAgentesService();
            Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            User u=(User)session.get("LOGGEDIN_USER");
            String login=u.getLogin();
            try {
                List<Agente> agentes=service.getAgentes();
                for(Agente a:agentes) {
                    if(a.getLogin().equals(login)) {
                        return a.getId();
                    }
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    	

    	
    	
    	//Filtrado por ciudad
    	public void listadoCiudad() {
    		PisosService service;
    		try {
    			service = Factories.services.createPisosService();
    			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    		Piso[] pisosCiudad;
    		int cont =0;
    		for(int i=0;i<pisos.length;i++) {
    			if(pisos[i].getCiudad().toString().equalsIgnoreCase(ciudad)) {
    				cont++;
    			}
    		}
    		pisosCiudad=new Piso[cont];
    		cont=0;
    		for(int i=0;i<pisos.length;i++) {
    			if(pisos[i].getCiudad().toString().equalsIgnoreCase(ciudad)) {
    				pisosCiudad[cont]=pisos[i];
    				cont++;
    			}
    		}
    		if (pisosCiudad.length!=0) {
    			this.setPisos(pisosCiudad);
    		}
    	}
    	
    	
    	//Filtrado por precio
    	public void filtraPorPrecio() {
    		PisosService service;
    		try {
    			service = Factories.services.createPisosService();
    			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		Piso[] pisosPrecio;
    		int cont = 0;
    		for(int i = 0; i < pisos.length;i++) {
    			if((pisos[i].getPrecio() >= precioMinimo) && (pisos[i].getPrecio()<=precioMaximo)) {
    				cont++;
    			}
    		}
    		pisosPrecio = new Piso[cont];
    		cont = 0;
    		for(int i = 0; i < pisos.length; i++) {
    			if((pisos[i].getPrecio() >= precioMinimo) && (pisos[i].getPrecio()<=precioMaximo)) {
    				pisosPrecio[cont] = pisos[i];
    				cont++;
    			}
    		}
    		this.setPisos(pisosPrecio);
    	}
    	
    	
    	public String baja(Piso piso) {
    		PisosService service;
    		try {
    			service = Factories.services.createPisosService();
    			service.deletePiso(piso.getId());
    			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
    			return "exito";

    		} catch (Exception e) {
    			e.printStackTrace();  
    			return "error";
    		}
    	}
    	
    	public String edit() {
    		PisosService service;
    		try {
    			service = Factories.services.createPisosService();
    			piso = (BeanPiso) service.findById(piso.getId());
    			return "exito";
    		} catch (Exception e) {
    			e.printStackTrace();  
    			return "error";
    		}
    	}
    	
    	public String salva() {
    		PisosService service;
    		try {
    			service = Factories.services.createPisosService();
    			if (piso.getId() == null) {
    				service.savePiso(piso);
    			}
    			else {
    				service.updatePiso(piso); 
    			} 
    			pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
    			this.listado();
    			return "exito";
    		} catch (Exception e) {
    			e.printStackTrace();
    			return "error";
    		}
    	}
    	
    	
    	
    	@PostConstruct
    	public void init() {    	  
    		System.out.println("BeanPisos - Ejecución PostConstruct"); 
    		ciudad = "";
    		piso = (BeanPiso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("piso"));
    		if (piso == null) { 
    			System.out.println("BeanPisos - No existia el piso");
    			piso = new BeanPiso();
    			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "piso", piso);
    		}
    	}
    	@PreDestroy
    	public void end()  {
    		System.out.println("BeanPisos - Eejecución PreDestroy");
    	}


		public Piso[] getPisosFiltrados() {
			return pisosFiltrados;
		}


		public void setPisosFiltrados(Piso[] pisosFiltrados) {
			this.pisosFiltrados = pisosFiltrados;
		}
	}


	
