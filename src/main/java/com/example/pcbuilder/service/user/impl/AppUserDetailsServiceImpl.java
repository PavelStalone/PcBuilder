package com.example.pcbuilder.service.user.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.domain.repository.other.contract.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class AppUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Log.d("loadUserByUsername called - username: " + username);

        return userRepository.findByUsername(username)
                .map(u ->
                        new User(
                                u.getUsername(),
                                u.getPassword(),
                                u.getRoles().stream()
                                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                                        .collect(Collectors.toList())
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
