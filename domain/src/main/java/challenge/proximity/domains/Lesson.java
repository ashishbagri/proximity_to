package challenge.proximity.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lesson {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToMany
    private List<Video> videos;

    @ManyToMany(mappedBy = "lessons")
    @JsonIgnore
    private List<Course> courses;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    @JsonIgnore
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
