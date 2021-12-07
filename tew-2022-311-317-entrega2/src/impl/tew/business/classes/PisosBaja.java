package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.AgentesDao;
import com.tew.persistence.PisosDao;
import com.tew.persistence.exception.NotPersistedException;

public class PisosBaja {

	public void delete(Long id) throws EntityNotFoundException {
		PisosDao dao = Factories.persistence.createPisosDao();
		try {
			dao.deletePiso(id);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Piso no eliminado " + id, ex);
		}
	}
}
