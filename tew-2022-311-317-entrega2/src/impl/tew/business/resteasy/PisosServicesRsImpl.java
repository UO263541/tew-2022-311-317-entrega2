package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.PisosServicesRs;
import com.tew.model.Piso;

import impl.tew.business.classes.*;

public class PisosServicesRsImpl implements PisosServicesRs {

	@Override
	public List<Piso> getPisos() {
		// TODO Auto-generated method stub
		try {
			return new PisosListado().getPisos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Piso findById(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return new PisosBuscar().find(id);
	}

	@Override
	public void deletePiso(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		new PisosBaja().delete(id);

	}

	@Override
	public void savePiso(Piso piso) throws EntityAlreadyExistsException {
		// TODO Auto-generated method stub
		new PisoAlta().save(piso);
	}

	@Override
	public void updatePiso(Piso piso) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		new PisosUpdate().update(piso);

	}

	@Override
	public void borrarDataBase() throws Exception {
		// TODO Auto-generated method stub
		new ResetBBDD().reiniciaBD();
		// Mejor crear un metodo aparte
	}

}
