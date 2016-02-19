package tagbar;

import org.flywaydb.core.Flyway;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import tagbar.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Hibernate Search による検索
 */
public class N01_HibernateSearch {

	public static void main(String[] args) throws Exception {
		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:h2:./db/section-8", "sa", "sa");
		flyway.migrate();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("section-8");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		// 全文検索インデックスの生成
		fullTextEntityManager.createIndexer().startAndWait();

		entityManager.getTransaction().begin();

		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(Book.class).get();
		org.apache.lucene.search.Query luceneQuery = qb
				.keyword()
				.onFields("title", "subtitle", "authors.name")
				.matching("ハイバネート")
				.createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Book.class);

		List<Book> results = jpaQuery.getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

		System.out.println("Count: " + results.size());
		results.stream().forEach(book -> System.out.println("Book: " + book));
	}
}
