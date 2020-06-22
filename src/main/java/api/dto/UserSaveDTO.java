package api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import api.domain.User;
import api.domain.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
public class UserSaveDTO {
	
	@NotBlank( message = "Username is required")
	@Size(min = 5, message = "Username must be at least 5 letters")
	private String username;
	
	@Email
	private String email;
	
	@NotBlank( message = "Password is required")
	@Size(min = 7, max = 20,message = "Password must be between 7 and 20")
	private String password;
	
	@NotNull( message = "Role is required")
	private Role role;
	

	public User TransformToUser() {
		return new User(null, this.username, this.email, this.password, this.role,true,null,null);
		
	}
	
}
