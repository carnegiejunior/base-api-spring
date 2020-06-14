package springcourse.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springcourse.domain.User;
import springcourse.repository.UserRepository;

@Service
public class UserLoginService {

	@Autowired
	UserRepository userRepository;
	
	public User login(String email, String password) {
		
		Optional<User> user = this.userRepository.login(email, password);
		User result = null;
		if (user.isPresent()){
			result = user.get();
		};
		return result;
	}
	
}
