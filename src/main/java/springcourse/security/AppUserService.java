package springcourse.security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springcourse.domain.User;

@Service
public class AppUserService implements UserDetailsService {

	@Autowired
	UserDetailRepository userDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> locatedUser = this.userDetailRepository.findByEmail(username);

		if (!locatedUser.isPresent())
			throw new UsernameNotFoundException("Dosen't exist user with email = " + username);

		

		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + locatedUser.get().getRole().name()));

		org.springframework.security.core.userdetails.User userService = 
				new org.springframework.security.core.userdetails.User(
						locatedUser.get().getEmail(), 
						locatedUser.get().getPassword(), 
						authorities);
		
		return userService;
	}

}
