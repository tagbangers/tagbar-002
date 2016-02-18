package tagbar;

import com.querydsl.jpa.impl.JPAQuery;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ogawa on 2016/02/12.
 */
public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = createEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(new Event());
		entityManager.persist(new Event());
		entityManager.getTransaction().commit();

		QEvent qEvent = QEvent.event;
		JPAQuery<Event> query = new JPAQuery<>(entityManager);
		List<Event> events = query.from(qEvent).fetch();
		events.stream().forEach(System.out::println);

		entityManager.close();
	}

	/**
	 * @see org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider#createContainerEntityManagerFactory(PersistenceUnitInfo, Map)
	 */
	private static EntityManagerFactory createEntityManagerFactory() {
		PersistenceUnitInfo info = createPersistenceUnitInfo();

		Map<String, Object> settings = new HashMap<>();
		settings.put("hibernate.dialect", H2Dialect.class);
		settings.put("hibernate.hbm2ddl.auto", "create-drop");
		settings.put("hibernate.show_sql", true);

		final List<String> mergedClassesAndPackages = new ArrayList<>(info.getManagedClassNames());
		return new EntityManagerFactoryBuilderImpl(
				new PersistenceUnitInfoDescriptor(info) {
					@Override
					public List<String> getManagedClassNames() {
						return mergedClassesAndPackages;
					}
				}, settings).build();
	}

	private static PersistenceUnitInfo createPersistenceUnitInfo() {
		DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
		persistenceUnitManager.setPackagesToScan("tagbar");
		persistenceUnitManager.setDefaultDataSource(createDataSource());
		persistenceUnitManager.afterPropertiesSet();
		return persistenceUnitManager.obtainDefaultPersistenceUnitInfo();
	}

	private static DataSource createDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:h2:./db/test");
		config.setUsername("sa");
		config.setPassword("sa");
		return new HikariDataSource(config);
	}
}
