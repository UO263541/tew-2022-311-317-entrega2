package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.AgentesServiceRs;
import com.tew.model.Agente;

import impl.tew.business.classes.AgentesMetodos;

public class AgentesServicesRsImpl implements AgentesServiceRs {

	@Override
	public Agente findById(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAgente(Agente agente) throws EntityAlreadyExistsException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAgente(Agente agente) throws EntityNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAgente(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetBD() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Agente> getAgentes() {
		// TODO Auto-generated method stub
				try {
					return new AgentesMetodos().getAgentes();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
	}

}
