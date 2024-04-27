package in.ashokit.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


/*@Data not use bcz when we use @Data anotation toString method come.if we try to print object of entity class java.lang.StackOverflowError: null
 *  exception come because due to bi-directional relation both entity class to string() call each other internally 
 *to fix use @Setter @Getter
 * */ 
@Setter
@Getter
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	private String fName;
	private String lName;
	private String email;
	private String pwd;
	
	@OneToMany(mappedBy="user" )
	private List<Post> p;

}
