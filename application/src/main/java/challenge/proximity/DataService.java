package challenge.proximity;

import challenge.proximity.domains.Course;
import challenge.proximity.domains.Tag;
import challenge.proximity.domains.User;

import java.util.Optional;

public interface DataService {
    Iterable<Course> getCourses();

    User getUsersById(long id);

    void addTag(Tag tag);

    Optional<Tag> findTagById(long id);
}
