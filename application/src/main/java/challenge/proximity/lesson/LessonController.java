package challenge.proximity.lesson;

import challenge.proximity.course.exception.CourseNotFoundException;
import challenge.proximity.domains.Lesson;
import challenge.proximity.repositories.LessonRepository;
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
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserService userService;
    
    @RequestMapping("/lessons")
    public Iterable<Lesson> getLesson(){
        return lessonRepository.findAll();
    }

    @DeleteMapping("/delete/lesson/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id, Principal principal) {
        Optional<Lesson> lesson = lessonRepository.findByIdAndUser_Name(id, principal.getName());
        if (!lesson.isPresent())
            return ResponseEntity.notFound().build();
        lessonRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lesson/{id}")
    public Lesson getLesson(@PathVariable long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);

        if (!lesson.isPresent())
            throw new CourseNotFoundException("id-" + id);

        return lesson.get();
    }

    @PutMapping("/edit/lesson/{id}")
    public ResponseEntity<Object> updateLesson(@RequestBody Lesson lesson, @PathVariable long id,Principal principal) {
        Optional<Lesson> less = lessonRepository.findByIdAndUser_Name(id, principal.getName());
        if (!less.isPresent())
            return ResponseEntity.notFound().build();
        lesson.setId(id);
        lesson.setUser(less.get().getUser());
        lessonRepository.save(lesson);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create/lesson")
    public ResponseEntity<Object> create(@RequestBody Lesson lesson) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        lesson.setUser(userService.findByUsername(currentPrincipalName));
        Lesson lesson1 = lessonRepository.save(lesson);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(lesson1.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/getLessonsWithCourse")
    public Iterable<Lesson> getLessons(@RequestParam String name){

        return lessonRepository.findByCourses_Name(name);
    }
}
