package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {
	/*find title by given input 
	 * likes search java
	 * find all title which contain(similar) java word
	 * */
	 @Query("SELECT p FROM Post p WHERE p.title LIKE %?1%")
	  List<Post> findByTitle(String title);

}
