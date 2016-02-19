package tagbar;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Event implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private String organizer;

	private LocalDate date;

	private String place;

	private BigDecimal fee;

	private int capacity;

	@Override
	public String toString() {
		return String.format("%d:%s @ %s by %s", id, name, place, organizer);
	}
}
