package challenge.proximity.domains;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name="Student")
public class Student extends User {

    @ManyToMany(mappedBy = "subscribers")
    private Set<Course> subscribedCourses = new HashSet<>();

    public Set<Course> getSubscribedCourses() {
        return subscribedCourses;
    }

    public void setSubscribedCourses(Set<Course> subscribedCourses) {
        this.subscribedCourses = subscribedCourses;
    }
}
