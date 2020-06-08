package springcourse.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springcourse.domain.Request;
import springcourse.domain.User;
import springcourse.dto.UserLoginDTO;
import springcourse.services.RequestService;
import springcourse.services.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {
	
	@Autowired UserService userService;
	@Autowired RequestService requestService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User createdUser = this.userService.save(user); 
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@RequestBody User user, @PathVariable(name = "id") Long id) {
		
		Optional<User> userFound = this.userService.getOptionalById(id);
		if (userFound.isPresent()) {

			BeanUtils.copyProperties(user, userFound.get(), "id");
			
			User updatedUser = this.userService.update(userFound.get()); 
			return ResponseEntity.ok(updatedUser);
		}
		return ResponseEntity.notFound().build();			
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
		User user = this.userService.getById(id); 
		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<List<User>> listAll() {
		return ResponseEntity.ok(this.userService.getAll());
	}
	
	
	
	@PostMapping( value = "/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDTO user) {
		return ResponseEntity.ok(this.userService.login(user.getEmail(), user.getPassword()));
	}
	
	@GetMapping(value = "/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestsByUserId(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok( this.requestService.listAllByOnwerId(id));
	}
	
	@PostMapping(value = "/{id}/requests")
	public ResponseEntity<Request> InsertRequestByUserId(@PathVariable(name = "id") Long id, @RequestBody Request request){
		Optional<User> user = this.userService.getOptionalById(id);
		if (user.isPresent()) {
			request.setOwner(user.get());
			return ResponseEntity.ok(this.requestService.save(request));
		}
		return ResponseEntity.notFound().build();		
	}	

}
