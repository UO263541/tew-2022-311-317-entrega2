package com.tew.persistence;

import java.util.List;

import com.tew.model.Agente;
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
public interface AgentesDao {

	List<Agente> getAgentes();
	void save(Agente a) throws AlreadyPersistedException;
	void update(Agente a) throws NotPersistedException;
	void delete(Long id) throws NotPersistedException;
	Agente findById(Long id);
	void reset() throws Exception; 

}