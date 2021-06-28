package challenge.proximity.course.service;

import challenge.proximity.domains.Course;
import challenge.proximity.domains.Subject;

import java.util.Optional;

public interface CourseService {

    public Course createCourse(Course course);
    public Optional<Course> findCourse(long courseId);
    public Iterable<Course> findAllCourses();
    public Course updateCourse(Course course);
    public void deleteCourse(Course course);

    public Iterable<Course> findBySubject(Subject subject);

    public Course subscribeToCourse(Course course);
    public Course unSubscribeToCourse(Course course);

    Optional<Course> findById(long id);

    void deleteById(long id);

    Optional<Course> findByIdAndUser_Name(long id, String name);

    Iterable<Course> findBySubjects_Name(String name);

    Iterable<Course> findBySubscribers_Name(String name);
}
