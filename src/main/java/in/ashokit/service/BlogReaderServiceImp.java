package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.binding.CommentForm;
import in.ashokit.binding.SearchBlog;
import in.ashokit.entity.Comment;
import in.ashokit.entity.Post;
import in.ashokit.repo.CommentRepo;
import in.ashokit.repo.PostRepo;
@Service
public class BlogReaderServiceImp implements BlogReaderService{
@Autowired
PostRepo postrepo;
@Autowired
CommentRepo crepo;

@Override
	public List<Post> allPost() {

	List<Post>	 p=postrepo.findAll();
	
	List<Post> check=new ArrayList<Post>();
	
	for(Post p1:p) {
	if(p1.isDeleted()==false)
	{
		check.add(p1);
	}
		
}

	return	check;
}
	

@Override
public Post findPost(Integer Id) {
Optional<Post> post=	postrepo.findById(Id);

if(post.isPresent()) {
	return post.get();}
return null;
}


@Override
public Post comment(CommentForm cf) {
	Optional<Post> p=postrepo.findById(cf.getBlogId());
	
	if(p.isPresent()) {
	
	Comment c=new Comment();
	BeanUtils.copyProperties(cf,c);
	c.setPost(p.get());

	
	crepo.save(c);
return c.getPost();
	}

	return null;


}

@Override
public List<Comment> getComment(Integer Id) {
	Optional<Post> post=	postrepo.findById(Id);
	Post p=post.get();
	System.out.print(p.getComment());
	return p.getComment();
}


@Override
public List<Post> SearchBlogReader(SearchBlog search) {
	/*Create Custom @Query  in repository interface to select on similar title containt when we search */
	
List<Post> p=	postrepo.findByTitle(search.getTitle());
List<Post> check=new ArrayList<Post>();
	
	for(Post p1:p) {
	if(p1.isDeleted()==false)
	{
		check.add(p1);
	}
		
}
			return check ;
	
}








}
