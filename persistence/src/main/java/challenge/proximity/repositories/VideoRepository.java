package challenge.proximity.repositories;

import challenge.proximity.domains.Course;
import challenge.proximity.domains.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends CrudRepository<Video,Long> {
    Optional<Video> findByIdAndUser_Name(long id, String name);

    Iterable<Video> findByLessons_Name(String name);

    Iterable<Video> findByTags_Name(String name);

    Iterable<Video> findByName(String name);
}
