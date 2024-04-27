package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.CreatePost;
import in.ashokit.entity.Comment;
import in.ashokit.entity.Post;

public interface CreatePostService {

	boolean saveCreatePostdata(CreatePost c, Integer userid);

	List<Post> allPost( Integer userId);
	public List<Comment> allComment(Integer userId);
	public void deletefromDatabase(Integer id);

	void softDelete(Integer id);
	public Post edit(Integer id);
}
