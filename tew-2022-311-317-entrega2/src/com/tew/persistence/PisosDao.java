package com.tew.persistence;

import java.util.List;

import com.tew.model.Piso;
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
public interface PisosDao {

	List<Piso> getPisos();
	void savePiso(Piso piso) throws AlreadyPersistedException;
	void updatePiso(Piso piso) throws NotPersistedException;
	void deletePiso(Long id) throws NotPersistedException;
	Piso findById(Long id);

}