package springcourse.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springcourse.domain.enums.RequestState;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RequestStage {
	private Long id;
	private Date realizationDate;
	private String description;
	private RequestState state;
	private Request request;
	private User user;

}
