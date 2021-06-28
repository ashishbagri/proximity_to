package challenge.proximity.repositories;

import challenge.proximity.domains.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagRespository extends CrudRepository<Tag, Long> {

    Iterable<Tag> findByUser_Name(String name);

    Optional<Tag> findByIdAndUser_Name(long id, String name);
}
