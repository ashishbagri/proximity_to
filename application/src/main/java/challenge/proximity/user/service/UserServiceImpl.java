package challenge.proximity.user.service;

import challenge.proximity.domains.User;
import challenge.proximity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
}
