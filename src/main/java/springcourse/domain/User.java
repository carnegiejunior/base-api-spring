package springcourse.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springcourse.domain.enums.Role;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User {
	private Long id;
	private String name;
	private String email;
	private String password;
	private List<Request> requests = new ArrayList<>();
	private List<RequestStage> stages = new ArrayList<>();
	private List<Role> roles = new ArrayList<>();
}
