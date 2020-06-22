package api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import api.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
public class UserUpdateDTO {
	
	@NotBlank( message = "User name is required")
	@Size(min = 5)
	private String username;
	
	@Email
	private String email;
	
	@NotBlank( message = "Password is required")
	@Size(min = 7, max = 20,message = "Password must be between 7 and 20")
	private String password;
	
	@NotNull(message = "User active is required")
	private boolean active;
	
	public User TransformToUser() {
		return new User(
				null, 
				this.username, 
				this.email, 
				this.password,
				null,
				this.active,
				null,null);
	}
	
}
