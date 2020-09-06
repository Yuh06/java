package vn.t3h.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "config")
@AllArgsConstructor
@NoArgsConstructor @Setter @Getter
public class Config implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	/* */
	private Integer id;
	@Column(name = "`key`")
	private String key; 
	@Column(name = "value")
	private String value;
	@Column(name = "note")
	private String note;

}
