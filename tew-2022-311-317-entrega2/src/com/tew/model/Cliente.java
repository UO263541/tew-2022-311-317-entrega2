package com.tew.model;

public class  Cliente{
	private Long id;
	private String login;
	private String passwd;
	private String rep_passwd;
	private String nombre;
	private String apellidos;
	private String email;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRep_passwd() {
		return rep_passwd;
	}
	public void setRep_passwd(String rep_passwd) {
		this.rep_passwd = rep_passwd;
	}
	
	
	
}
