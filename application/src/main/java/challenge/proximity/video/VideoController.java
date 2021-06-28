package challenge.proximity.video;

import challenge.proximity.course.exception.CourseNotFoundException;
import challenge.proximity.domains.Video;
import challenge.proximity.repositories.VideoRepository;
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
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/videos")
    public Iterable<Video> getAll(){
        return videoRepository.findAll();
    }

    @DeleteMapping("/delete/video/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id, Principal principal) {

        Optional<Video> video = videoRepository.findByIdAndUser_Name(id, principal.getName());
        if (!video.isPresent())
            return ResponseEntity.notFound().build();
        videoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/video/{id}")
    public Video get(@PathVariable long id, Principal principal) {
        Optional<Video> video = videoRepository.findById(id);

        if (!video.isPresent())
            throw new CourseNotFoundException("id-" + id);

        return video.get();
    }

    @PutMapping("/edit/video/{id}")
    public ResponseEntity<Object> updateVideo(@RequestBody Video video, @PathVariable long id,Principal principal) {

        Optional<Video> vid = videoRepository.findByIdAndUser_Name(id, principal.getName());
        if (!vid.isPresent())
            return ResponseEntity.notFound().build();
        video.setId(id);
        video.setUser(vid.get().getUser());
        videoRepository.save(video);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create/video")
    public ResponseEntity<Object> create(@RequestBody Video video) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        video.setUser(userService.findByUsername(currentPrincipalName));
        Video vid = videoRepository.save(video);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(vid.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/getVideosByLesson")
    public Iterable<Video> getVideos(@RequestParam String name){

        return videoRepository.findByLessons_Name(name);
    }

    @GetMapping("/getVideosByTagName")
    public Iterable<Video> getVideosByTag(@RequestParam String name){

        return videoRepository.findByTags_Name(name);
    }

    @GetMapping("/getVideosByName")
    public Iterable<Video> getVideosByName(@RequestParam String name){

        return videoRepository.findByName(name);
    }
}
