package challenge.proximity.subject;

import challenge.proximity.course.exception.CourseNotFoundException;
import challenge.proximity.domains.Subject;
import challenge.proximity.repositories.SubjectRepository;
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
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/subjects")
    public Iterable<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    @DeleteMapping("/delete/subject/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id, Principal principal) {
        Optional<Subject> sub = subjectRepository.findByIdAndUser_Name(id, principal.getName());
        if (!sub.isPresent())
            return ResponseEntity.notFound().build();
        subjectRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subject/{id}")
    public Subject getSubject(@PathVariable long id) {
        Optional<Subject> subject = subjectRepository.findById(id);

        if (!subject.isPresent())
            throw new CourseNotFoundException("id-" + id);

        return subject.get();
    }

    @PutMapping("/subjects/edit/{id}")
    public ResponseEntity<Object> updateCourse(@RequestBody Subject subject, @PathVariable long id,Principal principal) {

        Optional<Subject> subject1 = subjectRepository.findByIdAndUser_Name(id,principal.getName());

        if (!subject1.isPresent())
            return ResponseEntity.notFound().build();

        subject.setId(id);
        subject.setUser(subject1.get().getUser());
        subjectRepository.save(subject);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create/subject")
    public ResponseEntity<Object> create(@RequestBody Subject subject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        subject.setUser(userService.findByUsername(currentPrincipalName));

        Subject subject1 = subjectRepository.save(subject);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(subject1.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
