package springcourse.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springcourse.domain.enums.RequestState;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "requests_stages")
public class RequestStage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Column(name = "realization_date")
	private Date realizationDate;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private RequestState state;
	
	@ManyToOne
	@JoinColumn(name = "request_id")
	@NotNull
	private Request request;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	@NotNull
	private User owner;

}
