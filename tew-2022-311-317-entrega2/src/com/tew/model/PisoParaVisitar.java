package com.tew.model;

public class  PisoParaVisitar{
	
	private Long idPiso;
	private Long idCliente;
	private Long fechaHoraCita;//dd/mm/aaaa
	private int estadoVisita;//1, 2 o 3 -> 1:seleccionado para visita; 2-> citado por el agente pero no aceptado aun por cliente; 3-> aceptado por cliente para visitar.
	
	
	public Long getIdPiso() {
		return idPiso;
	}
	public void setIdPiso(Long idPiso) {
		this.idPiso = idPiso;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente (Long i) {
		this.idCliente = i;
	}
	public Long getFechaHoraCita() {
		return fechaHoraCita;
	}
	public void setFechaHoraCita(Long fechaHoraCita) {
		this.fechaHoraCita = fechaHoraCita;
	}
	public int getEstadoVisita() {
		return estadoVisita;
	}
	public void setEstadoVisita(int estadoVisita) {
		this.estadoVisita = estadoVisita;
	}
	
	
	
	
}
