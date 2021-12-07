package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.persistence.*;
import com.tew.persistence.exception.AlreadyPersistedException;

public class ResetBBDD {

	public void reiniciaBD() throws Exception {
		ResetBDDao dao = Factories.persistence.createResetBDDao();
		try {
			dao.resetbd();
		}
		catch (Exception ex) {
			throw new Exception("No se ha reiniciado la BD", ex);
		}
	}

}
