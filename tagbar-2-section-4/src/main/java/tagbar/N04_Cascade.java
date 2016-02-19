package tagbar;

import org.flywaydb.core.Flyway;
import tagbar.entity.Department;
import tagbar.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class N04_Cascade {

	public static void main(String[] args) {
		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:h2:./db/section-4", "sa", "sa");
		flyway.migrate();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("section-4");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		List<Employee> employee = entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
		if (employee != null) {
			System.out.println("Employee: " + employee);
		} else {
			System.out.println("No one!");
		}

		Department removeTarget = entityManager.find(Department.class, 2L);

		entityManager.getTransaction().begin();
		entityManager.remove(removeTarget);
		entityManager.getTransaction().commit();

		employee = entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
		if (employee != null) {
			System.out.println("Employee: " + employee);
		} else {
			System.out.println("No one!");
		}

		entityManager.close();
		entityManagerFactory.close();

	}
}
