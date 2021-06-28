package challenge.proximity.repositories;

import challenge.proximity.domains.Course;
import challenge.proximity.domains.Subject;
import challenge.proximity.domains.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course,Long> {

    List<Course> findBySubjects_Name(String name);

    Optional<Course> findByIdAndUser_Name(long id, String name);

    Iterable<Course> findBySubscribers_Name(String name);
}
