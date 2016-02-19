package tagbar;

import org.flywaydb.core.Flyway;
import tagbar.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * LazyInitializationException って？
 */
public class N01_LazyInitializationException {

	public static void main(String[] args) {
		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:h2:./db/section-4", "sa", "sa");
		flyway.migrate();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("section-4");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Employee employee = entityManager.createQuery(
				"select e " +
						"from Employee e " +
						"where " +
						"    e.username = :username",
				Employee.class)
				.setParameter("username", "ogawa")
				.getSingleResult();

//		entityManager.persist(new Event());
//		entityManager.persist(new Event());

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

//		System.out.println(employee.getDepartment());
//		System.out.println(employee.getProjects());
	}
}
