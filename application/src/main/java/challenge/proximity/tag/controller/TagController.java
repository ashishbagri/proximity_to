package challenge.proximity.tag.controller;

import challenge.proximity.course.exception.CourseNotFoundException;
import challenge.proximity.domains.Tag;
import challenge.proximity.domains.User;
import challenge.proximity.repositories.TagRespository;
import challenge.proximity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class TagController {

    @Autowired
    private TagRespository tagRespository;

    @Autowired
    private UserService userService;

    @GetMapping("/tags")
    public Iterable<Tag> getTags(){
        return tagRespository.findAll();
    }

    @DeleteMapping("/delete/taq/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable long id,Principal principal) {
        Optional<Tag> tag1 = tagRespository.findByIdAndUser_Name(id, principal.getName());
        if (!tag1.isPresent())
            return ResponseEntity.notFound().build();
        tagRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tag/{id}")
    public Tag getTag(@PathVariable long id) {
        Optional<Tag> tag = tagRespository.findById(id);

        if (!tag.isPresent())
            throw new CourseNotFoundException("id-" + id);

        return tag.get();
    }

    @PutMapping("/edit/tag/{id}")
    public ResponseEntity<Object> updateCourse(@RequestBody Tag tag, @PathVariable long id,Principal principal) {
        Optional<Tag> tag1 = tagRespository.findByIdAndUser_Name(id, principal.getName());
        if (!tag1.isPresent())
            return ResponseEntity.notFound().build();
        tag.setId(id);
        tag.setUser(tag1.get().getUser());
        tagRespository.save(tag);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create/tag")
    public ResponseEntity<Object> createTag(@RequestBody Tag tag, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        tag.setUser(user);
        Tag tag1 = tagRespository.save(tag);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(tag1.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
