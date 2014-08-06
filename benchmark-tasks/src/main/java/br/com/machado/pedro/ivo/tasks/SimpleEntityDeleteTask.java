package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.entity.factory.EntityFactory;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;
import br.com.machado.pedro.ivo.index.store.factory.IndexStoreFactory;
import br.com.machado.pedro.ivo.tasks.util.ContentGenerator;

/**
 * 
 * This task insert a new SimpleEntity
 * 
 * @author Pedro
 * 
 */
public class SimpleEntityDeleteTask implements Command {

	private SimpleEntity								entity;
	private static final String					TASK_ID		= "SIMPLE_ENTITY_DELETE";
	private static final OperationType	operation	= OperationType.DELETE;
	private Long id;

	public SimpleEntityDeleteTask(Long id) {
		this.id = id;
	}

	@Override
	public void execute() {
		/**
		 * Will update a SimpleEntity
		 * 
		 */
		Long totalTime = DAOFactory.createSimpleDAO().deleteById(this.id);
		IndexStoreFactory.getIndexStore().store(totalTime, operation, DAOFactory.createSimpleDAO().getEngine(), TASK_ID, null);
	}

	public boolean isEntityOk() {
		System.out.println(entity.toString());
		return (entity.getId() != null && entity.getBirthday() != null && entity.getCity() != null && entity.getEmail() != null && entity.getFirstname() != null && entity
				.getLastname() != null);
	}

	@Override
	public void run() {
		this.execute();
	}

}
