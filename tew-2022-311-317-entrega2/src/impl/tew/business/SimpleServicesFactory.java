package impl.tew.business;


import com.tew.business.PisosService;
import com.tew.business.AgentesService;
import com.tew.business.ClientesService;
import com.tew.business.LoginService;
import com.tew.business.PisosParaVisitarService;
import com.tew.business.ServicesFactory;

public class SimpleServicesFactory implements ServicesFactory {


	
	@Override
	public LoginService createLoginService() {
		return new SimpleLoginService();
	}

	@Override
	public AgentesService createAgentesService() {
		// TODO Auto-generated method stub
		return new SimpleAgentesService();
	}

	@Override
	public ClientesService createClientesService() {
		// TODO Auto-generated method stub
		return new SimpleClientesService();
	}

	@Override
	public PisosService createPisosService() {
		// TODO Auto-generated method stub
		return new SimplePisosService();
	}

	@Override
	public PisosParaVisitarService createPisosParaVisitarService() {
		// TODO Auto-generated method stub
		return new SimplePisosParaVisitarService();
	}

}
