package challenge.proximity.repositories;

import challenge.proximity.domains.Subject;
import challenge.proximity.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByName(String username);
}
