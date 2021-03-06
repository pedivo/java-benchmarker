package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.SimpleDAORedis;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import org.junit.Test;

public class SelectAllWithPaginationByIndexedAttributeTaskHandlerTest {

		@Test
		public void printEntity() {
				System.setProperty(DAOFactory.SIMPLE_DAO_FOR_NAME, SimpleDAORedis.class.getCanonicalName());
				System.setProperty(DAOFactory.DB_CONNECTION_POOL, "200");

				//Initialize
				SimpleDAO dao = DAOFactory.getInstance();
				DAOFactory.requeue(dao);

				Command task = new SelectAllWithPaginationByIndexedAttributeHandlerTask();
				task.execute();
		}
}
