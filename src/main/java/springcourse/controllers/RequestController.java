package springcourse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springcourse.domain.Request;
import springcourse.domain.RequestStage;
import springcourse.services.RequestService;
import springcourse.services.RequestStageService;

@RestController
@RequestMapping(value = "requests")
public class RequestController {
	
	@Autowired private RequestService requestService;
	@Autowired private RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.requestService.save(request));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody Request request) {
		request.setId(id);
		return ResponseEntity.ok(this.requestService.update(request));
	}
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(this.requestService.getById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Request>> listAll(){
		return ResponseEntity.ok(this.requestService.listAll());
	}

	@GetMapping(value = "/{ownerId}")
	public ResponseEntity<List<Request>> listAllByOwnerId(@PathVariable(name = "ownerId") Long id){
		return ResponseEntity.ok(this.requestService.listAllByOnwerId(id));
	}
	
	@GetMapping(value = "/{id}/request-stages")
	public ResponseEntity<List<RequestStage>> listAllStagesByRequestId(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(this.requestStageService.listAllByRequestId(id));
	}

	
}
