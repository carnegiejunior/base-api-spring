package api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
public class UserLoginDTO {
	
	@NotBlank(message = "User name is required")
	@Size(min = 5, max = 25, message = "The size of username must be between 5 and 25 characters")
	private String username;
	
	@NotBlank( message = "Password required")
	@Size(min = 3, max = 10)
	private String password;
}
