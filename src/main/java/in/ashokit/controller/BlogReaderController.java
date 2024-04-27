package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.CommentForm;
import in.ashokit.binding.SearchBlog;
import in.ashokit.entity.Comment;
import in.ashokit.entity.Post;
import in.ashokit.service.BlogReaderService;

@Controller
public class BlogReaderController {
@Autowired
BlogReaderService service;
	
	@GetMapping("/")
	public String showIndexPage(Model m) {
		/*bind search obj to index page*/
		m.addAttribute("search",new SearchBlog());

		m.addAttribute("blog", service.allPost());
		return "index";
	}
	/*when this method call BlogDetail page open*/
	@GetMapping("/BlogDetail")
	public String showBlogDetail(@RequestParam Integer blogId,Model m) {
		/*take id by url or find post blog detail and set to page*/
	     Post p=service.findPost(blogId);
		m.addAttribute("blog", p);
		
		
		CommentForm form=new CommentForm();
		/*also set post(blog) id in form object bcz we use in comment according to post comment store in database
		 * with the help of hidden variable
		 * */
		form.setBlogId(p.getPostId());	
		/*bind comment form object to form bcz to store data in binding object
		 * id already store in hidden varaiable ab agrr comment ko save krnege to id b jaygi sath m posy ki
		 *  */
		m.addAttribute("comment",form);
		/*get all comment according to blog 
		 **/
		List<Comment> L=service.getComment(blogId);
				m.addAttribute("showcomment", L);	
		return "BlogDetail";
	}

	/*store comment on database*/
	@PostMapping("/Commentupload")
	public String comment(@ModelAttribute("comment") CommentForm cf,Model m) {
		/*Get comment data from form and store into database */
		Post p=service.comment(cf);
		m.addAttribute("comment",cf);
	/* the again reload same page or send id also*/
		return "redirect:/BlogDetail?blogId="+ p.getPostId();
	}
	
	/*search method according to blog title it find blogs */
	
		@PostMapping("/Search")
		public String searchTitle(@ModelAttribute("search") SearchBlog title,Model m) {
			m.addAttribute("blog", service.SearchBlogReader(title));

			return "index";
		}

}
