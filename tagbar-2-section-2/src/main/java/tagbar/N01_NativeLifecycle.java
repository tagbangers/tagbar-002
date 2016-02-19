package tagbar;

import org.hibernate.LockMode;
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
import tagbar.entity.Event;

/**
 * エンティティのライフサイクル（ネイティブ版）
 *
 * http://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#pc
 */
public class N01_NativeLifecycle {

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

		// Transient
		Event event = new Event();
		System.out.println("New: " + session.contains(event));

		// Transient -> Persistent
		session.save(event);
		System.out.println("Session#save: " + session.contains(event));

//		event.setName("Hello");
//		session.flush();

		// Persistent -> Detached
		session.evict(event);
		System.out.println("Session#evict: " + session.contains(event));

		// Detached -> Persistent
		// Note: lock は JPA では非サポート。EntityManger の merge を使用するしかない
		session.lock(event, LockMode.NONE);
		System.out.println("Session#lock: " + session.contains(event));

		// Persistent -> Transient
		session.delete(event);
		System.out.println("Session#delete: " + session.contains(event));

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}
