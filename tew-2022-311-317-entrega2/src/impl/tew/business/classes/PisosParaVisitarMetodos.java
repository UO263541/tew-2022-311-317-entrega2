package impl.tew.business.classes;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.model.PisoParaVisitar;
import com.tew.persistence.ClientesDao;
import com.tew.persistence.PisosDao;
import com.tew.persistence.PisosParaVisitarDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;


public class PisosParaVisitarMetodos {
	PisosParaVisitarDao dao = Factories.persistence.createPisosParaVisitarDao();
	
	public void save(PisoParaVisitar piso) throws EntityAlreadyExistsException {
		try {
			dao.savePisoParaVisitar(piso);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("El piso para visitar ya existe " + piso, ex);
		}
	}
	
	public void delete(Long idPiso, Long idCliente) throws EntityNotFoundException {
		
		try {
			dao.deletePisoParaVisitar(idPiso, idCliente);;
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Piso para visitar no eliminado " + idPiso + "para el cliente: "+ idCliente, ex);
		}
	}
	
	public PisoParaVisitar find(Long idPiso, Long idCliente) throws EntityNotFoundException {
		
		PisoParaVisitar pisopv = dao.findByIds(idPiso,idCliente);
		if ( pisopv == null) {
			throw new EntityNotFoundException("No se ha encontrado el piso para visitar");
		}
		
		return pisopv;
	}
	
	public List<PisoParaVisitar> getPisos() throws Exception {
		// Aqu?????? iria l??????gica de negocio que ejecutase alg??????n proceso ...
		//... en este caso el ejemplo estan sencillo que no hay nada que hacer
		
		// Acceso a la capa de persistencia a traves de su fachada
		// La fachada se obtiene de la factor??????a
		
		return  dao.getPisosParaVisitar();

		// Aqu?????? podr??????a ir m??????s l??????gica de negocio que procesase los datos traidos 
		// de persistencia
		// ...
	}
	
	
	public void update(PisoParaVisitar piso) throws EntityNotFoundException {
		
		try {
			dao.updatePisoParaVisitar(piso);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Piso para visitar no eliminado " + piso, ex);
		}
	}

}
