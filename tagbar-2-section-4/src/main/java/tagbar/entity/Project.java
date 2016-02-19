package tagbar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Project implements Serializable {

	@Id
	private Long id;

	@ManyToMany
	private List<Employee> employees = new ArrayList<>();
}