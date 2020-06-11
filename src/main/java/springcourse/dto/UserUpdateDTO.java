package springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import springcourse.domain.Request;
import springcourse.domain.RequestStage;
import springcourse.domain.User;

@Getter @Setter
@ToString @EqualsAndHashCode
public class UserUpdateDTO {
	
	@NotBlank( message = "User name is required")
	@Size(min = 5)
	private String name;
	
	@Email
	private String email;
	
	@NotBlank( message = "Password is required")
	@Size(min = 7, max = 20,message = "Password must be between 7 and 20")
	private String password;
	
	private List<Request> requests = new ArrayList<>();
	private List<RequestStage> stages = new ArrayList<>();	
	
	public User TransformToUser() {
		return new User(null, this.name, this.email, this.password, null, this.requests, this.stages);
	}
	
}
