package challenge.proximity.user.service;

import challenge.proximity.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User currentUser = userService.findByUsername(s);
        UserDetails user = new org.springframework.security.core.userdetails.User(s, "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", true, true, true, true, AuthorityUtils.createAuthorityList(currentUser.isIs_instructor()?"instructor":"student"));
        return user;
    }
}
