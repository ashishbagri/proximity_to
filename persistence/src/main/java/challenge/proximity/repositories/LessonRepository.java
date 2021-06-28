package challenge.proximity.repositories;

import challenge.proximity.domains.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LessonRepository extends CrudRepository<Lesson,Long> {

    Optional<Lesson> findByIdAndUser_Name(long id, String name);

    Iterable<Lesson> findByCourses_Name(String name);
}
