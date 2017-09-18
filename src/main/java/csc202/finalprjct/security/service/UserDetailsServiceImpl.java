package csc202.finalprjct.security.service;

import csc202.finalprjct.entity.User;
import csc202.finalprjct.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        System.out.println("!@!@!@!@!@!@!@!@!@!@RUNNING loadUserByUsername");
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Collections.emptyList());
    }
}
