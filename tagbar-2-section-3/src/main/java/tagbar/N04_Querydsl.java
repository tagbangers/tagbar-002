package tagbar;

import com.querydsl.jpa.impl.JPAQuery;
import org.flywaydb.core.Flyway;
import tagbar.entity.Event;
import tagbar.entity.QEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class N04_Querydsl {

	public static void main(String[] args) {
		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:h2:./db/section-3", "sa", "sa");
		flyway.migrate();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("section-3");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		QEvent qEvent = QEvent.event;
		JPAQuery<Event> query = new JPAQuery<>(entityManager);
		List<Event> events = query.from(qEvent).where(qEvent.place.eq("横浜")).fetch();
		events.stream().forEach(System.out::println);

		entityManager.close();
	}
}
