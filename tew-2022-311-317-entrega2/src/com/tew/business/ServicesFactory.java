package com.tew.business;

import com.tew.business.LoginService;

public interface ServicesFactory {
	
	//AlumnosService createAlumnosService();

	LoginService createLoginService();
	AgentesService createAgentesService();
	ClientesService createClientesService();
	PisosService createPisosService();
	PisosParaVisitarService createPisosParaVisitarService();
	
	

}
