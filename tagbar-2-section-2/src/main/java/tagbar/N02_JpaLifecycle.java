package tagbar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * エンティティのライフサイクル（JPA 版）
 *
 * http://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#pc
 */
public class N02_JpaLifecycle {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		// Transient
		Event event = new Event();

		// Transient -> Persistent
		entityManager.persist(event);

//		event.setName("Hello");
//		session.flush();

		// Persistent -> Detached
		entityManager.detach(event);

		// Detached -> Persistent
		// Note: lock は JPA では非サポート。EntityManger の merge を使用するしかない
		event = entityManager.merge(event);

		// Persistent -> Transient
		entityManager.remove(event);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
