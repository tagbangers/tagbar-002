package tagbar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Department implements Serializable {

	@Id
	private Long id;

	@OneToMany(mappedBy = "department", cascade=CascadeType.REMOVE)
	private List<Employee> employees = new ArrayList<>();
}