package springcourse.dto;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import springcourse.domain.enums.Role;

@Getter @Setter
@ToString @EqualsAndHashCode
public class UserUpdateRoleDTO {
	
	@NotNull(message = "Role can't be null")
	private Role role;
}
