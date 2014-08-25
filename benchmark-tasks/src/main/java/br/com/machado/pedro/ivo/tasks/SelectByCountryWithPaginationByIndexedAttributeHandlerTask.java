package br.com.machado.pedro.ivo.tasks;

import br.com.machado.pedro.ivo.beans.enums.OperationType;
import br.com.machado.pedro.ivo.dao.factory.DAOFactory;
import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This task has the responsability to count how many entries there're for each country
 * and then starts other task just to Select those entries using limit and offset
 *
 * @author Pedro
 */
public class SelectByCountryWithPaginationByIndexedAttributeHandlerTask implements Command {
		private static ThreadPoolExecutor threadPool;

		public SelectByCountryWithPaginationByIndexedAttributeHandlerTask() {
				threadPool = new ThreadPoolExecutor(100, 100, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
		}

		@Override
		public void execute() {
				/**
				 * Will query by any country
				 */
				int total = 0;
				SimpleDAO dao = DAOFactory.createSimpleDAO();
				for (Country country : Country.values()) {
						Long totalTime = dao.countByIndexedCountry(country);
						dao.getResult();

						threadPool.submit(new SelectByCountryWithPaginationByIndexedAttributeTask((Long) dao.getResult(), country));
						total++;
				}

				while (threadPool.getCompletedTaskCount() < total) {
						try {
								Thread.sleep(20000);
						}
						catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
				}
		}

		@Override
		public void run() {
				this.execute();
		}

}
