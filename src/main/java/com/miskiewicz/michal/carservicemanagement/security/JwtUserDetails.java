package com.miskiewicz.michal.carservicemanagement.security;

import com.miskiewicz.michal.carservicemanagement.entities.User;
import com.miskiewicz.michal.carservicemanagement.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByEmail(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }else {
            System.out.println("found " + username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.get().getUserType().name()));
        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                authorities);
    }
}
