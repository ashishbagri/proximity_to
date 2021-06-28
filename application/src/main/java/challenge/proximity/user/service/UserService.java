package challenge.proximity.user.service;

import challenge.proximity.domains.User;

import java.util.Optional;

public interface UserService {

    public User register(User user);

    public User findByUsername(String name);

    Optional<User> findById(long id);
}
