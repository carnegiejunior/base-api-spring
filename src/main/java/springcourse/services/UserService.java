package springcourse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springcourse.domain.User;
import springcourse.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	
	public User save(User user) {
		return this.userRepository.save(user);
	}

	public User update(User user) {
		return this.userRepository.save(user);
	}
	

	public User getById(Long id) {
		return this.userRepository.findById(id).get();
	}

	public Optional<User> getOptionalById(Long id) {
		return this.userRepository.findById(id);
	}
	
	
	public List<User> getAll(){
		return this.userRepository.findAll();
	}
	
	public User login(String email, String password) {
		
		Optional<User> user = this.userRepository.login(email, password);
		User result = null;
		if (user.isPresent()){
			result = user.get();
		};
		return result;
	}
	
	
}
