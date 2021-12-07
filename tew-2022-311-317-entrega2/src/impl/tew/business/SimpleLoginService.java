package impl.tew.business;

import java.util.List;

import com.tew.business.AgentesService;
import com.tew.business.ClientesService;
import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.model.User;

public class SimpleLoginService implements LoginService {
	public User verify(String login, String password) {
		// TODO Auto-generated method stub
		ClientesService service = Factories.services.createClientesService();

		if(!validLogin(login, password)) {System.out.println("No encuentro al usuario"); return null;}
		if(validLogin(login, password)) {
			try {
				System.out.println("Encuentro al usuario");
				List<Cliente> lista = service.getClientes();
				for(Cliente c: lista) {
					if((c.getLogin().equals(login)) && (c.getPasswd().equals(password))) {
						return new User(login, "UsuarioCliente");
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return new User(login, "UsuarioAgente");
	}

	
	public boolean validLogin(String login, String password) {
		ClientesService serviceCliente = Factories.services.createClientesService();
		AgentesService serviceAgente = Factories.services.createAgentesService();
		String usuario=""; String contra="";
		try {
			System.out.println("En bucle");
			List<Cliente> listaClientes = serviceCliente.getClientes();
			List<Agente> listaAgentes = serviceAgente.getAgentes();
			System.out.println(listaAgentes.size());
			for(Cliente c: listaClientes) {
				System.out.println("USER CL: " + c.getLogin() + " PW: " + c.getPasswd());
				if((c.getLogin().equals(login)) && (c.getPasswd().equals(password))){
					usuario = c.getLogin().toString();
					contra = c.getPasswd().toString();	
				}
			}
			for(Agente a: listaAgentes) {
				System.out.println("USER AG: " + a.getLogin() + " PW: " + a.getPasswd());

				if((a.getLogin().equals(login)) && (a.getPasswd().equals(password))) {
					usuario = a.getLogin().toString();
					contra = a.getPasswd().toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LOGIN: " + login);
		System.out.println("PW: " + password);
		System.out.println("USUARIO: " + usuario);
		System.out.println("CONTRASEÑA: " +contra);
		return usuario.equals(login) && contra.equals(password);
	}

}