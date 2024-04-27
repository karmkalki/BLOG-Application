package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
