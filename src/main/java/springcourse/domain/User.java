package springcourse.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springcourse.domain.enums.Role;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "user")
public class User {
	
	@EqualsAndHashCode.Include
	@Id
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	private List<Request> requests = new ArrayList<>();
	private List<RequestStage> stages = new ArrayList<>();
	private List<Role> roles = new ArrayList<>();
}
