package tagbar;

import tagbar.entity.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA ブートストラップ（persistence.xml あり）
 *
 * http://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#bootstrap-jpa-compliant
 */
public class N02_JpaBootstrap1 {

	public static void main(String[] args) {
		// META-INF/persistence.xml から設定をロード
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		entityManager.persist(new Event());
		entityManager.persist(new Event());

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
