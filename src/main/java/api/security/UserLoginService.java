package api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.domain.User;
import api.repository.UserRepository;

@Service
public class UserLoginService {

	@Autowired
	UserRepository userRepository;
	
	public User login(String username, String password) {
		
		Optional<User> user = this.userRepository.login(username, password);
		User result = null;
		if (user.isPresent()){
			result = user.get();
		};
		return result;
	}
	
}
