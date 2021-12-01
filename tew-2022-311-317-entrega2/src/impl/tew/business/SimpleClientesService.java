package impl.tew.business;

import java.util.List;

import com.tew.business.ClientesService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Cliente;

import impl.tew.business.classes.ClientesMetodos;
import impl.tew.business.classes.ClientesMetodos;

public class SimpleClientesService implements ClientesService {

	@Override
	public List<Cliente> getClientes() throws Exception {
		// TODO Auto-generated method stub
		return new ClientesMetodos().getClientes();
		
	}

	@Override
	public Cliente findById(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return new ClientesMetodos().find(id);
	}

	@Override
	public void saveCliente(Cliente cliente) throws EntityAlreadyExistsException {
		// TODO Auto-generated method stub
		new ClientesMetodos().save(cliente);
	}

	@Override
	public void updateCliente(Cliente cliente) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		new ClientesMetodos().update(cliente);
	}

	@Override
	public void deleteCliente(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		new ClientesMetodos().delete(id);

	}

}
