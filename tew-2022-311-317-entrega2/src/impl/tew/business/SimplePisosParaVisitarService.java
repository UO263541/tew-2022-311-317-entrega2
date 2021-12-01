package impl.tew.business;

import java.util.List;

import impl.tew.business.classes.*;
import com.tew.business.PisosParaVisitarService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.PisoParaVisitar;

public class SimplePisosParaVisitarService implements PisosParaVisitarService {

	@Override
	public PisoParaVisitar findByIds(Long idPiso, Long idCliente) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return new PisosParaVisitarMetodos().find(idPiso, idCliente);
	}

	@Override
	public void savePisoParaVisitar(PisoParaVisitar PisoParaVisitar) throws EntityAlreadyExistsException {
		// TODO Auto-generated method stub
		new PisosParaVisitarMetodos().save(PisoParaVisitar);

	}

	@Override
	public void deletePisoParaVisitar(Long idPiso, Long idCliente) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		new PisosParaVisitarMetodos().delete(idPiso, idCliente);
	}

	@Override
	public void updatePisoParaVisitar(PisoParaVisitar PisoParaVisitar) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		new PisosParaVisitarMetodos().update(PisoParaVisitar);
	}

	@Override
	public List<PisoParaVisitar> getPisosParaVisitar() throws Exception {
		// TODO Auto-generated method stub
		return new PisosParaVisitarMetodos().getPisos();
	}

}
