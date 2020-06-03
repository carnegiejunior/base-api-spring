package springcourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springcourse.domain.Request;
import springcourse.domain.User;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
	public List<Request> findByOwnerId(Long id);
}
