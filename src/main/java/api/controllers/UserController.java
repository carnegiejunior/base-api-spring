package api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.domain.User;
import api.dto.UserLoginDTO;
import api.dto.UserLoginResponseDTO;
import api.dto.UserSaveDTO;
import api.dto.UserUpdateRoleDTO;
import api.model.PageModel;
import api.model.PageRequestModel;
import api.security.JwtManager;
import api.services.UserService;
import api.utils.PasswordEncoder;

@RestController
@RequestMapping(value = "users")
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private AuthenticationManager authManager;
	@Autowired private JwtManager jwtManager;
	
	@PostMapping
	@Secured({"ROLE_ADMINISTRATOR"})
	public ResponseEntity<User> save(@RequestBody @Valid UserSaveDTO user) {
		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		User createdUser = this.userService.save(user.TransformToUser()); 
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	

	@PutMapping(value = "/{id}")
	@PreAuthorize("@accessManager.isOwner(#id)")
	public ResponseEntity<User> update(@RequestBody @Valid User user, @PathVariable(name = "id") Long id) {
		
		Optional<User> userFound = this.userService.getOptionalById(id);
		if (userFound.isPresent()) {

			BeanUtils.copyProperties(user, userFound.get(), "id");
			
			User updatedUser = this.userService.update(userFound.get()); 
			return ResponseEntity.ok(updatedUser);
		}
		return ResponseEntity.notFound().build();			
	}
	
	//@Secured({"ROLE_ADMINISTRATOR"})
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id,
			@RequestBody @Valid UserUpdateRoleDTO userDTO) {
		User user = new User();
		user.setId(id);
		user.setRole(userDTO.getRole());
		this.userService.updateRole(user);
		return ResponseEntity.ok().build();
	}	

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
		User user = this.userService.getById(id); 
		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(
			@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "size", defaultValue = "5") int size) {
		
		Optional<PageRequestModel> optionalPageRequestModel = null;
		Optional<PageModel<User>> optionalUserPageModel = null;
		
		optionalPageRequestModel = Optional.ofNullable(new PageRequestModel(page, size));
		
		if (optionalPageRequestModel.isPresent()) {

			optionalUserPageModel = this.userService.listAllOnLazyMode(optionalPageRequestModel);
			if (optionalUserPageModel.isPresent()) {
				return ResponseEntity.ok(optionalUserPageModel.get());
			}
		
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping( value = "/login")
	public ResponseEntity<UserLoginResponseDTO> login(@RequestBody @Valid UserLoginDTO user) {
		
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
		
		Authentication auth = this.authManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		org.springframework.security.core.userdetails.User userSpring = 
				(org.springframework.security.core.userdetails.User) auth.getPrincipal();
		
		String email = userSpring.getUsername();
		
		List<String> roles = userSpring.getAuthorities()
				.stream()
				.map(authority -> authority.getAuthority() )
				.collect(Collectors.toList());
						
		
		
		return ResponseEntity.ok(this.jwtManager.createToken(email, roles));
		
	}
	
//	@GetMapping(value = "/{id}/requests")
//	public ResponseEntity<List<Request>> listAllRequestsByUserId(@PathVariable(name = "id") Long id){
//		return ResponseEntity.ok( this.requestService.listAllByOnwerId(id));
//	}
	
//	@PostMapping(value = "/{id}/requests")
//	public ResponseEntity<Request> InsertRequestByUserId(@PathVariable(name = "id") Long id, @RequestBody Request request){
//		Optional<User> user = this.userService.getOptionalById(id);
//		if (user.isPresent()) {
//			request.setOwner(user.get());
//			return ResponseEntity.ok(this.requestService.save(request));
//		}
//		return ResponseEntity.notFound().build();		
//	}

}
