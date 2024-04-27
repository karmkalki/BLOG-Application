package in.ashokit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.Loginform;
import in.ashokit.binding.RegisterForm;
import in.ashokit.entity.User;
import in.ashokit.repo.UserRepo;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImp implements UserService {
@Autowired
UserRepo urepo;

@Autowired
private HttpSession session;
	
	
	@Override
	public boolean registerUser(RegisterForm rg) {
		/*age obj aya mtlb email pehle se hai or agr null mtlb email nhi hai to condition true ho jaygai*/
	if(urepo.findByEmail(rg.getEmail())==null) {
		User u=new User();
		BeanUtils.copyProperties(rg,u);
		urepo.save(u);
		return true;
	}
return false;
}


	@Override
	public boolean login(Loginform lg) {
		User u=urepo.findByEmail(lg.getEmail());
		
			if(u==null) {			
			return false;
           		}
	
		if(u.getPwd().equals(lg.getPwd())) {
			session.setAttribute("userId", u.getUserId());
					
      	return true;}
		
		return false;
		

		
	}
}
