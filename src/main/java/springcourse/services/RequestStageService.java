package springcourse.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springcourse.domain.RequestStage;
import springcourse.repository.RequestRepository;
import springcourse.repository.RequestStageRepository;

@Service
public class RequestStageService {

	@Autowired
	private RequestStageRepository requestStageRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	public RequestStage save(RequestStage stage) {
		
		stage.setRealizationDate(new Date());
		
		RequestStage createdStage = this.requestStageRepository.save(stage); 
		
		this.requestRepository.updateStatus(
				stage.getRequest().getId(), 
				stage.getState() 
				);
		
		return createdStage;
		 
	}
	
	public RequestStage getById(Long id) {
		return this.requestStageRepository.findById(id).get();
	}
	
	
	public List<RequestStage> listAllByRequestId(Long id){
		
		return this.requestStageRepository.findAllByRequestId(id);
		
	}

	public List<RequestStage> listAll() {

		return this.requestStageRepository.findAll();
	}

}
