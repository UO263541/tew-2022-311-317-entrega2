package impl.tew.business;

import impl.tew.business.classes.*;


import java.util.List;

import com.tew.business.AgentesService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Agente;

/**
 * Clase de implementaciÃ³n (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimpleAgentesService implements AgentesService {

	@Override
	public List<Agente> getAgentes() throws Exception{
		return new AgentesMetodos().getAgentes();
	}

	@Override
	public void saveAgente(Agente agente) throws EntityAlreadyExistsException {
		//No sería necesario, no se puede registrar agentes nuevos
	}

	@Override
	public void updateAgente(Agente agente) throws EntityNotFoundException {
		//No sería necesario, no se puede modificar agentes
	}

	@Override
	public void deleteAgente(Long id) throws EntityNotFoundException {
		//No sería necesario, no se puede eliminar agentes
	}

	@Override
	public Agente findById(Long id) throws EntityNotFoundException {
		return new AgentesMetodos().find(id);
	}

	@Override
	public void resetBD() throws Exception {
		// TODO Auto-generated method stub
		new AgentesMetodos().reinicioBaseDeDatos();
		
	}
	
	
}
