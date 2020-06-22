package api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import api.domain.User;
import api.exceptions.NotFoundException;
import api.repository.UserRepository;

@Component("accessManager")
public class AccessManager {
	
	@Autowired
	UserRepository userRepository;
	

	public boolean isOwner(Long id) {
		String username = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal().toString();
		
		Optional<User> user = this.userRepository.findUserByUsername(username);

		if (!user.isPresent()) throw new NotFoundException("There are not user with id = "+username);
		return user.get().getId().equals(id);
	}
	
	
	public boolean isRequestOwner(Long id) {
		
		return false;
	}
	
}
