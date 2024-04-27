package in.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer commentId;
	private String name;
	private String email;
	/*to store long data*/
	@Lob
	@Column(columnDefinition = "longtext")
	private String comment;
	
	@CreationTimestamp

	private LocalDate uploadDate;

	@ManyToOne
	@JoinColumn(
			name="post_Id")
	private Post post;
	
}
