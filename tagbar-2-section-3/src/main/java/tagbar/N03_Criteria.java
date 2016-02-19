package tagbar;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
