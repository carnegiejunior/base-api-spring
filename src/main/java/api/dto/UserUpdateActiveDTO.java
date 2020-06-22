package api.dto;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
public class UserUpdateActiveDTO {
	
	@NotNull(message = "Active can't be null")
	private boolean active;
}
