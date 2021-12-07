package com.tew.model;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "piso")
public class  Piso implements Serializable{

	
	private Long id;
	private Long idAgente;
	private double precio;
	private String direccion;
	private int estado;
	private String ciudad;
	private int anio;
	private String foto;
	
	public Piso() {
	}
	
	
	
	public Piso(Long id, Long idAgente, double precio, String direccion, int estado, String ciudad, int anio,
			String foto) {
		super();
		this.id = id;
		this.idAgente = idAgente;
		this.precio = precio;
		this.direccion = direccion;
		this.estado = estado;
		this.ciudad = ciudad;
		this.anio = anio;
		this.foto = foto;
	}


	@XmlElement
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement
	public Long getIdAgente() {
		return idAgente;
	}
	public void setIdAgente(Long idAgente) {
		this.idAgente = idAgente;
	}
	
	@XmlElement
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@XmlElement
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@XmlElement
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	@XmlElement
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	@XmlElement
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}


	@XmlElement
	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

	
	
}
