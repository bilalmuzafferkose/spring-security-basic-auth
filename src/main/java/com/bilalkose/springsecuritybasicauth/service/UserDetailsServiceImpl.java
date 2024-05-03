package com.bilalkose.springsecuritybasicauth.service;

import com.bilalkose.springsecuritybasicauth.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //bunu UserService içinde de implemente edebilirdik ama single-responsibility'den uzaklaşmış olurduk
    //çünkü burası security'e ait bir alan. UserService ise kendi business'ımıza ait bir alan
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.getByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }
}
