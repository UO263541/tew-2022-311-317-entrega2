package impl.tew.business.classes;

import java.util.List;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Piso;
import com.tew.persistence.AgentesDao;
import com.tew.persistence.PisosDao;
import com.tew.persistence.exception.NotPersistedException;


public class AgentesMetodos {
	public List<Agente> getAgentes() throws Exception {
		// Aqu?????? iria l??????gica de negocio que ejecutase alg??????n proceso ...
		//... en este caso el ejemplo estan sencillo que no hay nada que hacer
		
		// Acceso a la capa de persistencia a traves de su fachada
		// La fachada se obtiene de la factor??????a
		AgentesDao dao = Factories.persistence.createAgentesDao();
		return  dao.getAgentes();
		

		// Aqu?????? podr??????a ir m??????s l??????gica de negocio que procesase los datos traidos 
		// de persistencia
		// ...
	}
	
	public Agente find(Long id) throws EntityNotFoundException {
		AgentesDao dao = Factories.persistence.createAgentesDao();
		Agente a = dao.findById(id);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado el agente");
		}
		
		return a;
	}
	
	public void reinicioBaseDeDatos() throws Exception {
		AgentesDao dao = Factories.persistence.createAgentesDao();
		try {
			dao.reset();
		} catch (Exception e) {
			throw new Exception("Error reinicio base de datos " + e);
		}
	}
	
}
