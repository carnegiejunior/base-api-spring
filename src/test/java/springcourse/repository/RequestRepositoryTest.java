package springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import springcourse.domain.Request;
import springcourse.domain.User;
import springcourse.domain.enums.RequestState;
import springcourse.domain.enums.Role;

@SpringBootTest
@DisplayName("Teste de repositórios de Request")
class RequestRepositoryTest {

	@Autowired RequestRepository requestRepository;
	@Autowired UserRepository userRepository;
	
	final static String USER_NAME_1 = "USER_TEST_ID_1";
	final static String EMAIL_USER = "EMAIL_FOR_USER_TEST@TESTE.LOCAL";
	final static String REQUEST_SUBJECT = "SUJEITO DA REQUISIÇÃO PARA TESTE";
	final static String REQUEST_DESCRIPTION = "DESCRIÇÃO DA REQUISIÇÃO PARA TESTE";
	

	@Test
	void deve_InserirUmPedido_Test() {
		
		User owner = new User(null,USER_NAME_1,EMAIL_USER,"123",Role.ADMINISTRATOR,null,null);
		Request request = new Request(null, REQUEST_SUBJECT, REQUEST_DESCRIPTION, new Date(), RequestState.OPEN, null, owner);

		User resultOwner = this.userRepository.save(owner);
		Request resultRequest = this.requestRepository.save(request);
		assertThat(resultRequest).isNotNull();
		this.requestRepository.delete(resultRequest);
		this.userRepository.delete(resultOwner);
		
	}
	
	@Test
	void deve_AtualizarStatusPedidoOpenParaInprogress_Test() {
		
		Request request = null;
		Request resultRequest  = null;
		User owner  = null;
		User resultOwner  = null;
		
		try {
			owner = new User(null,USER_NAME_1,EMAIL_USER,"123",Role.ADMINISTRATOR,null,null);
			resultOwner = this.userRepository.save(owner);
			request = new Request(null, REQUEST_SUBJECT, REQUEST_DESCRIPTION, new Date(), RequestState.OPEN, null, owner);
			resultRequest = this.requestRepository.save(request);
			int affectedRows = this.requestRepository.updateStatus(request.getId(), RequestState.IN_PROGRESS);
			assertThat(affectedRows).isGreaterThan(0);
		} finally {
			this.requestRepository.delete(resultRequest);
			this.userRepository.delete(resultOwner);
		}
		
		
	}
	

}
