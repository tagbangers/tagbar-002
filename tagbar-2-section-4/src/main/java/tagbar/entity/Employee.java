package tagbar.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee implements Serializable {

	@Id
	private Long id;

	@NaturalId
	private String username;

//	@Column(name = "pswd")
//	@ColumnTransformer(
//			read = "decrypt( 'AES', '00', pswd  )",
//			write = "encrypt('AES', '00', ?)"
//	)
//	private String password;
//
//	private int accessLevel;

	@ManyToOne(fetch = FetchType.LAZY)
	private Department department;

	@ManyToMany(mappedBy = "employees")
	private List<Project> projects = new ArrayList<>();
}