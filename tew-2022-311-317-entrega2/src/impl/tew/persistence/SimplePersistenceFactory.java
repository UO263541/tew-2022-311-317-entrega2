package impl.tew.persistence;


import com.tew.persistence.*;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author Enrique
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public AgentesDao createAgentesDao() {
		return new AgentesJdbcDao();
	}
	
	public ClientesDao createClientesDao() {
		return new ClientesJdbcDao();
	}

	@Override
	public PisosParaVisitarDao createPisosParaVisitarDao() {
		// TODO Auto-generated method stub
		return new PisosParaVisitarJdbcDao();
	}

	@Override
	public PisosDao createPisosDao() {
		// TODO Auto-generated method stub
		return new PisosJdbcDao();
	}

	@Override
	public ResetBDDao createResetBDDao() {
		// TODO Auto-generated method stub
		return new ResetBDJdbcDao();
	}
	

}
