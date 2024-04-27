package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ashokit.binding.CreatePost;
import in.ashokit.binding.SearchBlog;
import in.ashokit.entity.Post;
import in.ashokit.service.BlogReaderService;
import in.ashokit.service.CreatePostService;
import jakarta.servlet.http.HttpSession;


@Controller
public class CreateBlogCont {
	@Autowired
CreatePostService cservice;
	
	@Autowired
	BlogReaderService bservice;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/Post")
	public String yourAllPost(Model m) {
	Integer userId=	(Integer)session.getAttribute("userId");
	List<Post> L=cservice.allPost(userId);
		m.addAttribute("myallpost", L);
		return "Post";
	}
	
	
	@GetMapping("/NewPost")
	public String createPost(Model m) {
		m.addAttribute("create", new CreatePost());
		return "NewPost"; }
	
	@PostMapping("/NewPost")
	public String getDataToCreatePost(@ModelAttribute("create") CreatePost cp,Model m) {
Integer userid=(Integer)session.getAttribute("userId");		
	boolean create=	cservice.saveCreatePostdata(cp,userid);
	if(create) {
		m.addAttribute("BlogCreate", "Blog Upload Sucessfully");
	}else {
		m.addAttribute("BlogCreate", "Blog Not Upload ");
	}
	return "NewPost";
	}
	
	@GetMapping("/Comment")
	public String allComment(Model m) {
Integer userId=	(Integer)session.getAttribute("userId");

m.addAttribute("Allcomment", cservice.allComment(userId));
		
		return "Comment";
	}

	@GetMapping("/logout")
	public String logout() {
	session.invalidate();
	return "redirect:/";	
	}

		/*search your blog according to title*/
	@GetMapping("/AjaxSearch")
	public String searc(@RequestParam("data") String n,Model m) {
		SearchBlog s=new SearchBlog();
		s.setTitle(n);
		m.addAttribute("blog", bservice.SearchBlogReader(s));
		return "AjaxSearch";
	}

	@GetMapping("/Delete")
	public String delete(@RequestParam Integer id) {
	cservice.deletefromDatabase(id);
		
	return "redirect:/Comment";	
	}
	
	@GetMapping("/softDelete")
	public String softdelete(@RequestParam Integer id) {
	cservice.softDelete(id);
	System.out.print("call\n");
	return "redirect:/Post";	
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam Integer id,Model m) {
		
		Post p=cservice.edit(id);

CreatePost c=new CreatePost();
BeanUtils.copyProperties(p, c);
c.setId(id);

m.addAttribute("create", c);
return "NewPost";	
	}
	
	
}
