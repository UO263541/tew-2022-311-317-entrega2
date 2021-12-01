package com.tew.presentation;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.AgentesService;
import com.tew.business.ClientesService;
import com.tew.business.PisosParaVisitarService;
import com.tew.business.PisosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.model.Piso;
import com.tew.model.PisoParaVisitar;
import com.tew.model.User;

@ManagedBean
@SessionScoped
public class BeanPisosParaVisitar implements Serializable{
	      private static final long serialVersionUID = 6666876L;
		
        private PisoParaVisitar[] pisos = null;
        private Long fecha = null;
        
        //uso de inyeccion de dependencia
        @ManagedProperty(value="#{pisoPV}") 
    	private BeanPisoParaVisitar beanpisopv;
    	public Long getFecha() {
    		return fecha;
    	}

    	public void setFecha(Long fecha) {
    		this.fecha = fecha;
    	}
    	public BeanPisoParaVisitar getPiso() { 
    		return beanpisopv; 
    	}
    	public void setPiso(BeanPisoParaVisitar bean) {
    		this.beanpisopv = bean;
    	}
    	
    	
    	public PisoParaVisitar[] getPisos() {
    		return pisos;
    	}
    	
    	public void setPisos(PisoParaVisitar[] pisos) {
    		this.pisos = pisos;
    	}
    	
    	
    	
    	public Long getIdCliente() {
            ClientesService service=Factories.services.createClientesService();
            Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            User u=(User)session.get("LOGGEDIN_USER");
            String login=u.getLogin();
            try {
                List<Cliente> clientes=service.getClientes();
                for(Cliente c:clientes) {
                    if(c.getLogin().equals(login)) {
                        return c.getId();
                    }
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    	
    	
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
    	
    	
    	public String listadoCliente() {
    		PisosParaVisitarService service;
    		Long idc = this.getIdCliente();
    		try {
    			service = Factories.services.createPisosParaVisitarService();
    			pisos = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
    			PisoParaVisitar[] pisosFiltrados;
    			int cont = 0;
    			for(int i = 0; i < pisos.length;i++) {
    				if((pisos[i].getIdCliente() == idc) ) {
    					cont++;
    				}
    			}
    			pisosFiltrados = new PisoParaVisitar[cont];
    			cont = 0;
    			for(int i = 0; i < pisos.length; i++) {
    				if((pisos[i].getIdCliente() == idc)) {
    					pisosFiltrados[cont] = pisos[i];
    					cont++;
    				}
    			}
    			this.setPisos(pisosFiltrados);

    			return "exito";

    		} catch (Exception e) {
    			e.printStackTrace();  
    			return "error";
    		}
    	}
    	
    	
    	public String listadoAgente() {
    		PisosParaVisitarService service;
    		PisosService serviceP;
    		Long ida = this.getIdAgente();
    		try {
    			service = Factories.services.createPisosParaVisitarService();
    			serviceP =Factories.services.createPisosService();
    			pisos = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
    			Piso p = serviceP.findById(pisos[0].getIdPiso());
    			PisoParaVisitar[] pisosFiltrados;
    			int cont = 0;
    			for(int i = 0; i < pisos.length;i++) {
    				p = serviceP.findById(pisos[i].getIdPiso());
    				if(p.getIdAgente()==ida) {
    					cont++;
    				}
    			}
    			pisosFiltrados = new PisoParaVisitar[cont];
    			cont = 0;
    			for(int i = 0; i < pisos.length; i++) {
    				if((serviceP.findById(pisos[i].getIdPiso()).getIdAgente()==ida)) {
    					pisosFiltrados[cont] = pisos[i];
    					cont++;
    				}
    			}
    			this.setPisos(pisosFiltrados);

    			return "exito";

    		} catch (Exception e) {
    			e.printStackTrace();  
    			return "error";
    		}
    	}
    
    	
    	
    	public String baja(PisoParaVisitar piso) {
    		PisosParaVisitarService service;
    		try {
    			service = Factories.services.createPisosParaVisitarService();
    			service.deletePisoParaVisitar(piso.getIdPiso(), piso.getIdCliente());
    			pisos = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
    			return "exito";

    		} catch (Exception e) {
    			e.printStackTrace();  
    			return "error";
    		}
    	}
    	
    	
    	public String edit() {
    		PisosParaVisitarService service;
    		try {
    			service = Factories.services.createPisosParaVisitarService();
    			beanpisopv = (BeanPisoParaVisitar) service.findByIds(beanpisopv.getIdPiso(), beanpisopv.getIdCliente());
    			return "exito";
    		} catch (Exception e) {
    			e.printStackTrace();  
    			return "error";
    		}
    	}
    	
    	
    	public String establecerVisita (Long idp) {
    		Long idc = this.getIdCliente();
    		PisosParaVisitarService service;
    		
    		try {
    			Calendar cal = Calendar.getInstance();
    			Long f = cal.getTime().getTime();
    			this.setFecha(f);
    		
    			service = Factories.services.createPisosParaVisitarService();

				this.beanpisopv.setEstadoVisita(2);
				this.beanpisopv.setFechaHoraCita(f);
				this.beanpisopv.setIdPiso(idp);
				this.beanpisopv.setIdCliente(idc);
				
				service.updatePisoParaVisitar(beanpisopv);
    			
    			pisos = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
    			this.listadoAgente();
    			
    			return "exito";
    			}
    			catch (Exception e) {
    				e.printStackTrace();  
    				return "error";
    			}
    	}
    	
    	
    	
    	public String confirma (Long idp) {
    		PisosParaVisitarService service;
    		
    		try {
    			Long idc = getIdCliente();
    			service = Factories.services.createPisosParaVisitarService();
    			
    			this.beanpisopv.setEstadoVisita(3);
    			this.beanpisopv.setIdPiso(idp);
    			this.beanpisopv.setFechaHoraCita(getFecha());
    			this.beanpisopv.setIdCliente(idc);
				service.updatePisoParaVisitar(beanpisopv);
    				
      			pisos = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
    			return "exito";

    		} catch (Exception e) {
    			e.printStackTrace();
    			return "error";
    		}
    	}
    	
    	
    	public String salva(Long idp) {
    		PisosParaVisitarService service;
    		try {
    			Long idc = getIdCliente();
    			service = Factories.services.createPisosParaVisitarService();
 
				this.beanpisopv.setIdPiso(idp);
				this.beanpisopv.setIdCliente(idc);
				service.savePisoParaVisitar(beanpisopv);
    				
    		
    			pisos = (PisoParaVisitar [])service.getPisosParaVisitar().toArray(new PisoParaVisitar[0]);
    			return "exito";

    		} catch (Exception e) {
    			e.printStackTrace();
    			return "error";
    		}
    	}
    	
    	
    	//Función inicia piso
    	public void iniciaPisoParaVisitar(ActionEvent event) {
    	    FacesContext facesContext = FacesContext.getCurrentInstance();
    	    ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
    	    this.beanpisopv.setIdPiso(null);
    	    this.beanpisopv.setIdCliente(new Long(4));
    	    this.beanpisopv.setFechaHoraCita(0L);
    	    this.beanpisopv.setEstadoVisita(1);
    	  }	
    	
    	

    	@PostConstruct
    	public void init() {    	  
    		System.out.println("BeanPisos - Eejecución PostConstruct"); 
    		beanpisopv = (BeanPisoParaVisitar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("pisoPV"));
    		if (pisos == null) { 
    			System.out.println("BeanPisos - No existia el piso");
    			beanpisopv = new BeanPisoParaVisitar();
    			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "pisoPV", beanpisopv);
    		}
    	}
    	@PreDestroy
    	public void end()  {
    		System.out.println("BeanPisos - Eejecución PreDestroy");
    	}
	}


	
