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
import tagbar.entity.Event;

/**
 * Hibernate ネイティブのブートストラップ
 *
 * http://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#bootstrap-native
 */
public class N01_NativeBootstrap {

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

		session.save(new Event());
		session.save(new Event());

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}
