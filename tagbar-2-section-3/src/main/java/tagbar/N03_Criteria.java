package tagbar;

import org.flywaydb.core.Flyway;
import tagbar.entity.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class N03_Criteria {

	public static void main(String[] args) {
		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:h2:./db/section-3", "sa", "sa");
		flyway.migrate();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("section-3");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Event> query = builder.createQuery(Event.class);
		Root<Event> root = query.from(Event.class);
		query.select(root).where(builder.equal(root.get("place"), "横浜"));
		List<Event> events = entityManager.createQuery(query).getResultList();
		events.stream().forEach(System.out::println);

		entityManager.close();
	}
}
