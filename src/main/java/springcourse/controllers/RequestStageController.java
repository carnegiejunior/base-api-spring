package springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springcourse.domain.RequestStage;
import springcourse.services.RequestStageService;

@RestController
@RequestMapping(value = "request-stage")
public class RequestStageController {

	@Autowired private RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<RequestStage> save(RequestStage requestStage){
		return ResponseEntity.status(HttpStatus.CREATED).body(requestStageService.save(requestStage));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(this.requestStageService.getById(id));
	}
	
}
