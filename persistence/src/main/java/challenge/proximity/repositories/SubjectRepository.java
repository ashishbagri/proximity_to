package challenge.proximity.repositories;

import challenge.proximity.domains.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubjectRepository extends CrudRepository<Subject,Long> {
    Optional<Subject> findByIdAndUser_Name(long id, String name);
}
