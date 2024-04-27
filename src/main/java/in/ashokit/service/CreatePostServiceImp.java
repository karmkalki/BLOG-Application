package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.ashokit.binding.CreatePost;
import in.ashokit.entity.Comment;
import in.ashokit.entity.Post;
import in.ashokit.entity.User;
import in.ashokit.repo.CommentRepo;
import in.ashokit.repo.PostRepo;
import in.ashokit.repo.UserRepo;

@Service
public class CreatePostServiceImp implements CreatePostService {
@Autowired
UserRepo urepo;
	
@Autowired
PostRepo prepo;
	
@Autowired
CommentRepo crepo;

/*to create new post*/
@Override
	public boolean saveCreatePostdata(CreatePost c,Integer userid) {
		Optional<User> check=urepo.findById(userid);
		if(check.isPresent()) {
		User u=check.get();
			Post p=new Post();
		BeanUtils.copyProperties(c,p);
		p.setDeleted(false);
p.setUser(u);
/* for edit otherwise edit not work */
p.setPostId(c.getId());
prepo.save(p);	
return true;		
		}
		return false;}


/*to find all blog(post)*/
@Override
public List<Post> allPost(Integer userId) {
/*According to user Specific*/
	Optional<User> u=urepo.findById(userId);
User user=new User();

if(u.isPresent()) {	
user=u.get();
}

List<Post> p=user.getP();
List<Post> check=new ArrayList<Post>();
for(Post p1:p) {
	if(p1.isDeleted()==false)
	{
		check.add(p1);
	}
		
}

	return	check;
}

/* to display all comment*/
public List<Comment> allComment(Integer userId) {
	/*According to user specific*/
	Optional<User> u=urepo.findById(userId);
	User user=new User();
	if(u.isPresent()) {	
	user=u.get();}
	List<Post> p=user.getP();
	List<Comment> c=new ArrayList<Comment>();
	
	for(Post post:p) {
	for(Comment c2:post.getComment()) {
		c.add(c2);
	}
		
		
	}
	
	
	
return	c;
}

public void deletefromDatabase(Integer id) {
	crepo.deleteById(id);
}


@Override
public void softDelete(Integer id) {
Optional<Post> L=prepo.findById(id);
if(L.isPresent()) {
Post p=	L.get();
System.out.println(p);
p.setDeleted(true);
prepo.save(p);
}
	
}
public Post edit(Integer id) {
	
	Optional<Post> p=prepo.findById(id);
	
	if(p.isPresent()) {
	
		return p.get();
	}
	return null;
}




}
;