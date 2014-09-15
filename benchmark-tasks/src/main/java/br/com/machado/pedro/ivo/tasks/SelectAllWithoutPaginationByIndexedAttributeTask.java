package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.index.store.factory.IndexStoreFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * That task will just do a simple count query by a indexed field
 *
 * @author Pedro
 */
public class SelectAllWithoutPaginationByIndexedAttributeTask implements Command {

		private static final String        TASK_ID   = "SELECT_ALL_WITHOUT_PAGINATION_BY_INDEXED_ATTIRBUTE";
		private static final OperationType operation = OperationType.SELECT;
		private Country country;

		public SelectAllWithoutPaginationByIndexedAttributeTask(Country country) {
				this.country = country;
		}

		@Override
		public void execute() {
				/**
				 * Will query by any country
				 */
				SimpleDAO dao = DAOFactory.getInstance();

				if (dao.isIndexQueriesSupported()) {
						Long totalTime = dao.selectByIndexedCountry(country);

						Map<String, Object> metadata = new HashMap<>();
						metadata.put("country", this.country.toString());

						IndexStoreFactory.getIndexStore().store(totalTime, operation, dao.getEngine(), TASK_ID, metadata);
				}

				DAOFactory.requeue(dao);
		}

		@Override
		public void run() {
				this.execute();
		}

}
