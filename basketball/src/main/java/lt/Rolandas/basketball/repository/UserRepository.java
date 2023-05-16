package lt.Rolandas.basketball.repository;

import lt.Rolandas.basketball.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllBy(Pageable pageable);


}
