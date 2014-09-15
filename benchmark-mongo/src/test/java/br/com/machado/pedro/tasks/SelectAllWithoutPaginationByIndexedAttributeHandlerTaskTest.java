package br.com.machado.pedro.tasks;

import br.com.machado.pedro.ivo.dao.SimpleDAOMongoDB;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.tasks.Command;
import br.com.machado.pedro.ivo.tasks.SelectAllWithoutPaginationByIndexedAttributeHandlerTask;
import org.junit.Test;

public class SelectAllWithoutPaginationByIndexedAttributeHandlerTaskTest {

		@Test
		public void printEntity() {
				System.setProperty(DAOFactory.SIMPLE_DAO_FOR_NAME, SimpleDAOMongoDB.class.getCanonicalName());

				//Initialize
				SimpleDAO dao = DAOFactory.getInstance();
				DAOFactory.requeue(dao);
				Command task = new SelectAllWithoutPaginationByIndexedAttributeHandlerTask();
				task.execute();
		}
}
