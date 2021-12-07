package impl.tew.business.classes;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.model.Piso;
import com.tew.persistence.AgentesDao;
import com.tew.persistence.ClientesDao;
import com.tew.persistence.PisosDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class ClientesMetodos {
	ClientesDao dao = Factories.persistence.createClientesDao();
	
	public void save(Cliente cliente) throws EntityAlreadyExistsException {
		try {
			dao.saveCliente(cliente);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("El cliente ya existe " + cliente, ex);
		}
	}
	
	public void delete(Long id) throws EntityNotFoundException {
		try {
			dao.deleteCliente(id);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Cliente no eliminado " + id, ex);
		}
	}
	
	public Cliente find(Long id) throws EntityNotFoundException {
		
		Cliente c = dao.findById(id);
		if ( c == null) {
			throw new EntityNotFoundException("No se ha encontrado el cliente");
		}
		
		return c;
	}
	
	public List<Cliente> getClientes() throws Exception {
		// Aqu?????? iria l??????gica de negocio que ejecutase alg??????n proceso ...
		//... en este caso el ejemplo estan sencillo que no hay nada que hacer
		
		// Acceso a la capa de persistencia a traves de su fachada
		// La fachada se obtiene de la factor??????a
		
		return  dao.getClientes();

		// Aqu?????? podr??????a ir m??????s l??????gica de negocio que procesase los datos traidos 
		// de persistencia
		// ...
	}
	
	public void update(Cliente cliente) throws EntityNotFoundException {
		
		try {
			dao.updateCliente(cliente);;
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Cliente no eliminado " + cliente, ex);
		}
	}
	
	
}
