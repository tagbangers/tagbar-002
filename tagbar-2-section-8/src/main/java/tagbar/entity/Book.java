package tagbar.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
@Analyzer(definition = "custom")
@Getter
@Setter
public class Book extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Field
	private String title;

	@Field
	private String subtitle;

	@ManyToMany
	@IndexedEmbedded
	private Set<Author> authors = new HashSet<>();

	private Date publicationDate;

	@Override
	public String toString() {
		return getTitle();
	}
}