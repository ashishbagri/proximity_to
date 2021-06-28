package challenge.proximity.domains;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name="Instructor")
public class Instructor extends User {

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private Set<Tag> tags = new HashSet<>();
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private Set<Lesson> lessons = new HashSet<>();
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private Set<Video> videos = new HashSet<>();

}
