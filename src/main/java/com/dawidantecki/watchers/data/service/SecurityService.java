package com.dawidantecki.watchers.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService myUserDetailsService;

    @Autowired
    public SecurityService(AuthenticationManager authenticationManager,
                           UserDetailsService myUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
    }

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    public String findLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void autoLogin(String username, String password) {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities()
        );

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
    }
}
