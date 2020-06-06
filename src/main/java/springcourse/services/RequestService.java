package springcourse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springcourse.domain.Request;
import springcourse.domain.enums.RequestState;
import springcourse.repository.RequestRepository;

@Service
public class RequestService {

		@Autowired private RequestRepository requestRepository;
		
		public Request save(Request request) {
			request.setState(RequestState.OPEN);
			//request.setCreationDate(new Date());
			return this.requestRepository.save(request);
		}
		
		public Request update(Request request) {
			return this.requestRepository.save(request);
		}
		
		public Request getById(Long id) {
			return this.requestRepository.findById(id).get();
		}
		
		public List<Request> listAll(){
			return this.requestRepository.findAll();
		}
		
		public List<Request> listAllByOnwerId(Long id){
			return this.requestRepository.findAllByOwnerId(id);
		}
}
