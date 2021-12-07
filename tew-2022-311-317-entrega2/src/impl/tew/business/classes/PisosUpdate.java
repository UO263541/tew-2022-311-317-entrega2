package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.persistence.AgentesDao;
import com.tew.persistence.PisosDao;
import com.tew.persistence.exception.NotPersistedException;

public class PisosUpdate {

	public void update(Piso piso) throws EntityNotFoundException {
		PisosDao dao = Factories.persistence.createPisosDao();
		try {
			dao.updatePiso(piso);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Piso no eliminado " + piso, ex);
		}
	}

}
