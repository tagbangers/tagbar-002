package tagbar;

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
 * Spring JPA のブートストラップ
 *
 * @see org.springframework.orm.jpa.persistenceunit.SmartPersistenceUnitInfo
 * @see org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider#createContainerEntityManagerFactory(javax.persistence.spi.PersistenceUnitInfo, java.util.Map)
 */
public class N04_SpringJpaBootstrap {

	public static void main(String[] args) {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:h2:./db/test");
		config.setUsername("sa");
		config.setPassword("sa");
		DataSource dataSource = new HikariDataSource(config);

		DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
		persistenceUnitManager.setPackagesToScan("tagbar");
		persistenceUnitManager.setDefaultDataSource(dataSource);
		persistenceUnitManager.afterPropertiesSet();
		PersistenceUnitInfo info = persistenceUnitManager.obtainDefaultPersistenceUnitInfo();

		Map<String, Object> settings = new HashMap<>();
		settings.put("hibernate.dialect", H2Dialect.class);
		settings.put("hibernate.hbm2ddl.auto", "update");
		settings.put("hibernate.show_sql", true);
		settings.put("hibernate.format_sql", true);

		final List<String> mergedClassesAndPackages = new ArrayList<>(info.getManagedClassNames());
		EntityManagerFactory entityManagerFactory = new EntityManagerFactoryBuilderImpl(
				new PersistenceUnitInfoDescriptor(info) {
					@Override
					public List<String> getManagedClassNames() {
						return mergedClassesAndPackages;
					}
				}, settings).build();

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(new Event());
		entityManager.persist(new Event());
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
