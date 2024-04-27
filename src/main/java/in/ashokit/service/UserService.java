package in.ashokit.service;

import in.ashokit.binding.Loginform;
import in.ashokit.binding.RegisterForm;

public interface UserService {
public boolean registerUser(RegisterForm rg) ;
public boolean login(Loginform lg) ;
}
