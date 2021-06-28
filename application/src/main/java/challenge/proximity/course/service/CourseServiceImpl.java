package challenge.proximity.course.service;

import challenge.proximity.domains.Course;
import challenge.proximity.domains.Subject;
import challenge.proximity.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findCourse(long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Iterable<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Course course) {
        return null;
    }

    @Override
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public Iterable<Course> findBySubject(Subject subject) {
        return courseRepository.findBySubjects_Name(subject.getName());
    }

    @Override
    public Course subscribeToCourse(Course course) {
        return null;
    }

    @Override
    public Course unSubscribeToCourse(Course course) {
        return null;
    }

    @Override
    public Optional<Course> findById(long id) {
        return courseRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<Course> findByIdAndUser_Name(long id, String name) {
        return courseRepository.findByIdAndUser_Name(id,name);
    }

    @Override
    public Iterable<Course> findBySubjects_Name(String name) {
        return courseRepository.findBySubjects_Name(name);
    }

    @Override
    public Iterable<Course> findBySubscribers_Name(String name) {
        return courseRepository.findBySubscribers_Name(name);
    }
}
