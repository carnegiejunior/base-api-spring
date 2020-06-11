package springcourse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springcourse.domain.User;
import springcourse.domain.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT DISTINCT u FROM users u WHERE u.email = ?1 AND u.password = ?2")
	public Optional<User> login(String email, String password);

	@Query("SELECT DISTINCT u FROM users u WHERE u.email = ?1")
	public Optional<User> findUserByEmail(String email);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE users SET role = ?2 WHERE id = ?1")
	public int updateRole(Long id, Role role);
	
}
