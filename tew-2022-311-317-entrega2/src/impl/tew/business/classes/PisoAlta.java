package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.persistence.AgentesDao;
import com.tew.persistence.PisosDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class PisoAlta {

	public void save(Piso piso) throws EntityAlreadyExistsException {
		PisosDao dao = Factories.persistence.createPisosDao();
		try {
			dao.savePiso(piso);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("El piso ya existe " + piso, ex);
		}
	}

}
