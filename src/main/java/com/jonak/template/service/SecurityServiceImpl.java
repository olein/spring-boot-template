package com.jonak.template.service;


import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
public class SecurityServiceImpl implements SecurityService {

    private static Logger log = Logger.getLogger(SecurityServiceImpl.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Boolean authenticateUser(String name, String password, String role) {

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(name, password));
            log.info("In authentication start");
            List<GrantedAuthority> list = new ArrayList(authentication.getAuthorities());
            if (role.contains(String.valueOf(list.get(0)))) {
                log.info("User authenticated");
                return true;
            }
        } catch (Exception e) {
            log.log(java.util.logging.Level.WARNING, "User not authenticated");
            return false;
        }
        return false;
    }
}
