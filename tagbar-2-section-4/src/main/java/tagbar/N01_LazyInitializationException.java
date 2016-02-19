package tagbar;

import org.flywaydb.core.Flyway;
import org.hibernate.LazyInitializationException;
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

		String jpql = "select e from Employee e where e.username = :username";
		Employee employee = entityManager.createQuery(jpql, Employee.class)
				.setParameter("username", "ogawa")
				.getSingleResult();

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

		try {
			// Note: フェッチしてない場合は、javassist による偽物？
			System.out.println(employee.getDepartment().getClass());
			// Note: フェッチしてない場合は、PersistentBag であり、 ArrayList ではない
			System.out.println(employee.getProjects().getClass());

			employee.getDepartment().getId();
		} catch (LazyInitializationException e) {
			e.printStackTrace();
		}
	}
}
