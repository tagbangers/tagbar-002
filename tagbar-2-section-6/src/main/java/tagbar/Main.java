package tagbar;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

/**
 *
 *
 * http://docs.jboss.org/hibernate/orm/5.1/userguide/html_single/Hibernate_User_Guide.html#multitenacy
 */
public class Main {

	public static void main(String[] args) {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:h2:./db/test");
		config.setUsername("sa");
		config.setPassword("sa");
		DataSource dataSource = new HikariDataSource(config);


		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
