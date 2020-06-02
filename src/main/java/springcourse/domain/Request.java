package springcourse.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springcourse.domain.enums.RequestState;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Request {
	private Long id;
	private String subject;
	private String description;
	private Date creationDate; 
	private RequestState requestState;
	private User user;
	private List<RequestStage> requestStages = new ArrayList<>();
}
