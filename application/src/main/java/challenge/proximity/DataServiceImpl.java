package challenge.proximity;

import challenge.proximity.course.service.CourseService;
import challenge.proximity.domains.Course;
import challenge.proximity.domains.Tag;
import challenge.proximity.domains.User;
import challenge.proximity.repositories.TagRespository;
import challenge.proximity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DataServiceImpl implements DataService{

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagRespository tagRespository;

    @Override
    public Iterable<Course> getCourses() {
        return courseService.findAllCourses();
    }

    @Override
    public User getUsersById(long id) {
        return userService.findById(id).get();
    }

    @Override
    public void addTag(Tag tag) {
        tagRespository.save(tag);
    }

    @Override
    public Optional<Tag> findTagById(long id) {
        return tagRespository.findById(id);
    }
}
