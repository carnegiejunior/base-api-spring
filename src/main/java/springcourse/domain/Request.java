package springcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

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
@Entity(name = "requests")
public class Request implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	private String subject;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date creationDate; 
	
	@Enumerated(EnumType.STRING)
	private RequestState state;
	
	@OneToMany(mappedBy = "request")
	private List<RequestStage> stages = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
}
