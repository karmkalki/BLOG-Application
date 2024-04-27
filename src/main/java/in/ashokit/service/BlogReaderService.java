package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.CommentForm;
import in.ashokit.binding.SearchBlog;
import in.ashokit.entity.Comment;
import in.ashokit.entity.Post;

public interface BlogReaderService {
	public List<Post> allPost();
	public Post findPost(Integer Id);
	public Post comment(CommentForm cf);
	public List<Comment> getComment(Integer Id);
	public List<Post> SearchBlogReader(SearchBlog search);

}
