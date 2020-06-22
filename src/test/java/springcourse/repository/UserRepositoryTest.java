package springcourse.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import api.repository.UserRepository;

@SpringBootTest
@DisplayName("Teste de repositórios de usuário")
class UserRepositoryTest {

	@Autowired UserRepository userRepository;
	final static String USER_NAME_1 = "USER_TEST_ID_1";
	final static String USER_NAME_2 = "USER_TEST_ID_2";
	final static String EMAIL_USER = "EMAIL_FOR_USER_TEST@TESTE.LOCAL";

	
//    @Before  
//    public void setup() {  
//       // Locale.setDefault(new Locale("en", "US"));  
//    }  
//  
//    @After  
//    public void cleanUp() {  
//        //Locale.setDefault(localeOriginal);  
//    }  
	
//	@Test
//	void deve_RetornaUsuarioLogado_Test() {
//		
//		User user1Entity = new User(null,USER_NAME_1,EMAIL_USER,"123",Role.ADMINISTRATOR);
//		
//		this.userRepository.save(user1Entity);
//		
//		Optional<User> loggedUser = userRepository.login(EMAIL_USER, "123");
//		
//		assertThat(loggedUser.get().getEmail()).isEqualTo(EMAIL_USER);
//		
//		this.userRepository.delete(loggedUser.get());
//	
//	}
//	
//	@Test
//	void deve_AtualizarRoleUsuario() {
//		
//		User user = new User(null,USER_NAME_1,EMAIL_USER,"123",Role.SIMPLE);
//		
//		this.userRepository.save(user);
//		
//		Optional<User> findedUser = this.userRepository.findUserByEmail(EMAIL_USER);
//		
//		int affectedRows = this.userRepository.updateRole(findedUser.get().getId(), Role.ADMINISTRATOR);
//		
//		assertThat(affectedRows).isEqualTo(1);
//		
//		this.userRepository.delete(findedUser.get());
//		
//	}
	

}
