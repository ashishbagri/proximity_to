package challenge.proximity.domains;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    protected long userId;

    protected String name;

    protected String password;

    protected boolean is_instructor;

    public User(String name, String p, boolean is_instructor) {
        this.name = name;
        password = p;
        this.is_instructor = is_instructor;
    }

    public User() {
    }

    public long getuserId() {
        return userId;
    }

    public void setuserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_instructor() {
        return is_instructor;
    }

    public void setIs_instructor(boolean is_instructor) {
        this.is_instructor = is_instructor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
