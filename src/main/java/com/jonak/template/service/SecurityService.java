package com.jonak.template.service;

public interface SecurityService {

    Boolean authenticateUser(String name, String password, String role);
}
