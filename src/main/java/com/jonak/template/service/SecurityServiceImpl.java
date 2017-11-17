package com.jonak.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Boolean authenticateUser(String name, String password, String role) {

        try{
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(name, password));

            if (String.valueOf(authentication.getAuthorities()).contains(role)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
