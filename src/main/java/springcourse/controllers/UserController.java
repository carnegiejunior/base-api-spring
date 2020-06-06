package springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springcourse.domain.User;
import springcourse.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public User login(RequestBody userBody, String email, String password) {
		return this.userService.login(email, password);
	}

}
