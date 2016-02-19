package tagbar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.H2Dialect;

import java.util.List;

public class N01_HQL {

	public static void main(String[] args) {
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.applySetting(AvailableSettings.URL, "jdbc:h2:./db/test")
				.applySetting(AvailableSettings.USER, "sa")
				.applySetting(AvailableSettings.PASS, "sa")
				.applySetting(AvailableSettings.DRIVER, "org.h2.Driver")
				.applySetting(AvailableSettings.DIALECT, H2Dialect.class)
				.applySetting(AvailableSettings.HBM2DDL_AUTO, "update")
				.applySetting(AvailableSettings.SHOW_SQL, true)
				.applySetting(AvailableSettings.FORMAT_SQL, true)
				.build();

		Metadata metadata = new MetadataSources(standardRegistry)
				.addAnnotatedClass(Event.class)
				.getMetadataBuilder()
				.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
				.build();

		SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();
		SessionFactory sessionFactory = sessionFactoryBuilder.build();

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		Event event = new Event();
		event.setName("Tag Bar 1");
		event.setOrganizer("Tagbangers");
		event.setPlace("横浜");
		event.setCapacity(20);
		session.save(event);

		event = new Event();
		event.setName("Tag Bar 2");
		event.setOrganizer("Tagbangers");
		event.setPlace("渋谷");
		event.setCapacity(50);
		session.save(event);

		event = new Event();
		event.setName("Other Bar");
		event.setOrganizer("Other company");
		event.setPlace("横浜");
		event.setCapacity(30);
		session.save(event);

		List<Event> events = session.createQuery("from Event event where event.place = :place")
				.setParameter("place", "横浜")
				.list();
		events.stream().forEach(System.out::println);

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

}
