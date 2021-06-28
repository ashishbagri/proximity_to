package challenge.proximity.course.contoller;

import challenge.proximity.course.exception.CourseNotFoundException;
import challenge.proximity.course.service.CourseService;
import challenge.proximity.domains.Course;
import challenge.proximity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @GetMapping("/courses")
    public Iterable<Course> getCourses(){
        return courseService.findAllCourses();
    }

    @DeleteMapping("/delete/course/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable long id, Principal principal) {
        Optional<Course> course = courseService.findByIdAndUser_Name(id, principal.getName());
        if (!course.isPresent())
            return ResponseEntity.notFound().build();
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course/{id}")
    public Course retrieveCourse(@PathVariable long id) {
        Optional<Course> course = courseService.findById(id);

        if (!course.isPresent())
            throw new CourseNotFoundException("id-" + id);

        return course.get();
    }

    @PutMapping("/edit/course/{id}")
    public ResponseEntity<Object> updateCourse(@RequestBody Course course, @PathVariable long id,Principal principal) {

        Optional<Course> course1 = courseService.findByIdAndUser_Name(id, principal.getName());
        if (!course1.isPresent())
            return ResponseEntity.notFound().build();
        course.setId(id);
        course.setUser(course1.get().getUser());
        courseService.createCourse(course);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create/course")
    public ResponseEntity<Object> createCourse(@RequestBody Course course) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        course.setUser(userService.findByUsername(currentPrincipalName));
        Course saveCourse1 = courseService.createCourse(course);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveCourse1.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/getCoursesWithSubject")
    public Iterable<Course> getCourses(@RequestParam String name){

        return courseService.findBySubjects_Name(name);
    }

    @GetMapping("/getSubscriptions")
    public Iterable<Course> getCourses(Principal user){
        return courseService.findBySubscribers_Name(user.getName());
    }

    @GetMapping("/subscribe/course/{id}")
    public ResponseEntity<Object> subscribe(@PathVariable long id,Principal principal) {

        Optional<Course> c = courseService.findCourse(id);
        if(!c.isPresent())
            return ResponseEntity.notFound().build();
        c.get().getSubscribers().add(userService.findByUsername(principal.getName()));
        courseService.createCourse(c.get());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(c.get().getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping("/unsubscribe/course/{id}")
    public ResponseEntity<Object> unsubscribe(@PathVariable long id,Principal principal) {

        Optional<Course> c = courseService.findCourse(id);
        if(!c.isPresent())
            return ResponseEntity.notFound().build();
        c.get().getSubscribers().remove(userService.findByUsername(principal.getName()));
        courseService.createCourse(c.get());
        return ResponseEntity.noContent().build();

    }

}
