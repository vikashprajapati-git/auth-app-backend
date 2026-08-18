package com.auth.security;

import com.auth.exceptions.ResourceNotFoundException;
import com.auth.repositories.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomeUserDetailService implements UserDetailsService {

    private final UserRepositry userRepositry;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositry.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Invalid email or password!!"));
    }
}
