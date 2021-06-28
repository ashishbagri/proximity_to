package challenge.proximity;

import challenge.proximity.domains.*;
import challenge.proximity.repositories.LessonRepository;
import challenge.proximity.repositories.SubjectRepository;
import challenge.proximity.repositories.TagRespository;
import challenge.proximity.repositories.VideoRepository;
import challenge.proximity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
@EntityScan("challenge.proximity.domains")
@EnableJpaRepositories("challenge.proximity.repositories")
public class MainApp {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TagRespository tagRespository;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {


            User student = new User("Student","pass",false);
            userService.register(student);

            User instructor = new User("Instructor","pass",true);
            userService.register(instructor);

            Tag tag1 = new Tag();
            tag1.setName("tag1");
            tag1.setDescription("Sample Tag for Tag1");
            tag1.setUser(student);

            Tag tag2 = new Tag();
            tag2.setName("tag2");
            tag2.setDescription("Sample Tag for tag2");
            tag2.setUser(instructor);

            tagRespository.save(tag1);
            tagRespository.save(tag2);

            Video video = new Video();
            video.setName("video");
            video.setLink("link");
            video.setTags(Arrays.asList(tag1,tag2));

            video.setUser(instructor);
            videoRepository.save(video);

            Lesson lesson = new Lesson();
            lesson.setName("lesson");
            lesson.setVideos(Arrays.asList(video));

            lesson.setUser(instructor);
            lessonRepository.save(lesson);

            Subject s = new Subject();
            s.setName("science");
            s.setUser(instructor);
            Course c = new Course();
            c.setName("couse1");
            c.setUser(instructor);
            s.setCourses(Arrays.asList(c));
            c.setLessons(Arrays.asList(lesson));
            subjectRepository.save(s);
        };
    }
}
