package tagbar;

import org.flywaydb.core.Flyway;
import tagbar.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;

/**
 * EntityGraph による動的フェッチ
 */
public class N03_Fetch_EntityGraph {

	public static void main(String[] args) {
		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:h2:./db/section-4", "sa", "sa");
		flyway.migrate();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("section-4");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Employee employee = entityManager.find(Employee.class, 1L, Collections.singletonMap(
						"javax.persistence.fetchgraph",
						entityManager.getEntityGraph( "employee.graph" )
				)
		);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

		System.out.println("Department: " + employee.getDepartment().getId());
	}
}
