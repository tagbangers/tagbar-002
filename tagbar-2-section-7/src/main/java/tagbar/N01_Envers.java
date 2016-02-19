package tagbar;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import tagbar.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Envers を使った監査ログの記録
 *
 * http://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#envers
 */
public class N01_Envers {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("section-7");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Person person = new Person();
		person.setName("小川");
		person.setMobile("090-0000-0000");
		entityManager.persist(person);
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		person.setName("小川岳史");
		entityManager.persist(person);
		entityManager.flush();
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		person.setMobile("090-1111-1111");
		entityManager.persist(person);
		entityManager.flush();
		entityManager.getTransaction().commit();

		AuditReader auditReader = AuditReaderFactory.get(entityManager);

		AuditQuery query = auditReader
				.createQuery()
				.forEntitiesAtRevision(Person.class, 2);
		Person revision = (Person) query.getSingleResult();
		System.out.println("Revision2 name: " + revision.getName());
		System.out.println("Revision2 mobile: " + revision.getMobile());

		entityManager.close();
		entityManagerFactory.close();
	}
}
