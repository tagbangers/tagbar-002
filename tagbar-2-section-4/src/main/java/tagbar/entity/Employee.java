package tagbar.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(name = "employee.graph",
		attributeNodes = {
				@NamedAttributeNode("department"),
				@NamedAttributeNode("projects"),
		}
)
@Getter
@Setter
public class Employee implements Serializable {

	@Id
	private Long id;

	@NaturalId
	private String username;

	@ManyToOne(fetch = FetchType.LAZY)
//	@ManyToOne(fetch = FetchType.EAGER)
	private Department department;

	@ManyToMany(mappedBy = "employees")
	private List<Project> projects = new ArrayList<>();
}