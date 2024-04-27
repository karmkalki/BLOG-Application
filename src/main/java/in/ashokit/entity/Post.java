package in.ashokit.entity;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString

public class Post {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer postId;
private String title;
private String description;
/*to store long(large) data like blog content */
@Lob
@Column(columnDefinition = "longtext")
private String content;

@CreationTimestamp
@Column(nullable=false,updatable=false)
private LocalDate createDate;

@UpdateTimestamp
private LocalDate editDate;

private boolean deleted;

@OneToMany(mappedBy ="post",
// if we delete the post in data comment also delete this is working of cascade.remove
cascade=CascadeType.REMOVE )
private List<Comment> comment;

@ManyToOne
@JoinColumn(name="user_Id")
private User user;

}

