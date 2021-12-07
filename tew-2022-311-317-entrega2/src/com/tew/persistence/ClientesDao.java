package com.tew.persistence;

import java.util.List;

import com.tew.model.Cliente;
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
public interface ClientesDao {

	List<Cliente> getClientes();
	void saveCliente(Cliente c) throws AlreadyPersistedException;
	void updateCliente(Cliente c) throws NotPersistedException;
	void deleteCliente(Long id) throws NotPersistedException;
	Cliente findById(Long id);

}