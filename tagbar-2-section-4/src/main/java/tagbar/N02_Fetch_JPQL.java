package tagbar;

import org.flywaydb.core.Flyway;
import org.hibernate.LazyInitializationException;
import tagbar.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPQL による動的フェッチ
 */
public class N02_Fetch_JPQL {

	public static void main(String[] args) {
		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:h2:./db/section-4", "sa", "sa");
		flyway.migrate();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("section-4");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		String jpql =
				"select e from Employee e " +
				"left join fetch e.department " +
				"left join fetch e.projects " +
				"where e.username = :username";
		Employee employee = entityManager.createQuery(jpql, Employee.class)
				.setParameter("username", "ogawa")
				.getSingleResult();

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

		System.out.println("Department: " + employee.getDepartment().getId());
	}
}
