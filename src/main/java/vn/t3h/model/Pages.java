
package vn.t3h.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "pages")
@Entity
@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
public class Pages implements Serializable {

	private static final long serialVersionUID = 4641853311314844969L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@Column(name="cate")
	private Integer cate;
	
	@NotEmpty(message = "Ten bai hat khong duoc de trong")
	@Column(name="name")
	private String name;
	
	@Column(name="title")
	private String title;
	
	@Column(name="`desc`")
	private String desc;
	
	@Column(name="content")
	private String content;
	
	@Column(name="`status`")
	private String status;
	
	@Column(name="create_time")
	private String createTime;
}
