package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.Loginform;
import in.ashokit.binding.RegisterForm;
import in.ashokit.service.UserService;
import in.ashokit.service.UserServiceImp;

@Controller
public class UserController {
@Autowired
UserServiceImp uservice;


	
	@GetMapping("/SignUp")
	public String showSignUpPage(Model m) {
m.addAttribute("register", new RegisterForm());
	return "SignUp";
	}
	
	@PostMapping("/SignUp")
	public String storeSignUpPageData(@ModelAttribute("register") RegisterForm rg, Model m) {

		boolean check=uservice.registerUser(rg);
		if(check) {
			m.addAttribute("sucess", "Register Succefully!");
		}else {
			m.addAttribute("unsuccess", "Already,Email Available!");
		}
			return "SignUp";
			}

	@GetMapping("/login")
	public String showloginPage(Model m) {
m.addAttribute("login", new Loginform());
		return "login";
	}
	
	@PostMapping("/login")
	public String checkloginPageData(@ModelAttribute("login")Loginform lg  ,Model m) {
		boolean check=uservice.login(lg);
		
		
		if(check) {
			return "redirect:/Post";
		}else {
			m.addAttribute("unsuccess", "Invalid Credentials");
		}
		return "login";
	}




}
