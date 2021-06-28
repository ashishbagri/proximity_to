package challenge.proximity.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Video {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String  link;

    @ManyToMany(mappedBy = "videos")
    @JsonIgnore
    private List<Lesson> lessons = new ArrayList<>();

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
