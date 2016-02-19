package tagbar;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import tagbar.entity.Event;
import tagbar.support.PersistenceUnitInfoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;

/**
 * JPA ブートストラップ（persistence.xml なし）
 * <p/>
 * http://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#bootstrap-jpa-hibernate
 * @see PersistenceUnitInfoImpl
 */
public class N03_JpaBootstrap2 {

	public static void main(String[] args) {
		String persistenceUnitName = "section-1";

		List<String> entityClassNames = new ArrayList<>();
		entityClassNames.add(Event.class.getCanonicalName());

		Properties properties = new Properties();
		properties.put(AvailableSettings.URL, "jdbc:h2:./db/section-1");
		properties.put(AvailableSettings.USER, "sa");
		properties.put(AvailableSettings.PASS, "sa");
		properties.put(AvailableSettings.DRIVER, "org.h2.Driver");
		properties.put(AvailableSettings.DIALECT, H2Dialect.class);
		properties.put(AvailableSettings.HBM2DDL_AUTO, "update");
		properties.put(AvailableSettings.SHOW_SQL, true);
		properties.put(AvailableSettings.FORMAT_SQL, true);

		PersistenceUnitInfoImpl persistenceUnitInfo = new PersistenceUnitInfoImpl(
				persistenceUnitName,
				entityClassNames,
				properties
		);

		EntityManagerFactoryBuilderImpl entityManagerFactoryBuilder =
				new EntityManagerFactoryBuilderImpl(
						new PersistenceUnitInfoDescriptor(persistenceUnitInfo),
						null
				);

		EntityManagerFactory entityManagerFactory = entityManagerFactoryBuilder.build();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(new Event());
		entityManager.persist(new Event());

		entityManager.getTransaction().commit();
		entityManager.close();

		entityManagerFactory.close();
	}
}
