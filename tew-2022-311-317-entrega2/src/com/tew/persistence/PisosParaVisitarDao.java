package com.tew.persistence;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.model.Piso;
import com.tew.model.PisoParaVisitar;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

/**
 * Interfaz de la fachada a servicios de persistencia para la entidad Alumno.
 * 
 * En esta versi��n aparecen los otros m��todos b��sicos de un servicio 
 * de persistencia
 * 
 * @author alb
 *
 */
public interface PisosParaVisitarDao {

	//Tanto para el service como para aqu� tengo que revisar los m�todos update, por lo demas parece todo bien

	PisoParaVisitar findByIds(Long idPiso, Long idCliente);
	void savePisoParaVisitar(PisoParaVisitar PisoParaVisitar) throws AlreadyPersistedException;
	void deletePisoParaVisitar(Long idPiso, Long idCliente) throws NotPersistedException;
	List<PisoParaVisitar> getPisosParaVisitar();
	void updatePisoParaVisitar(PisoParaVisitar PisoParaVisitar) throws NotPersistedException;
	
}