package com.cricket.local_score.Configuration;

import com.cricket.local_score.Entity.UserEntity;
import com.cricket.local_score.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user=userRepo.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found with username "+email));

        if (user.getEmail()==null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username or password is null");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }


}
