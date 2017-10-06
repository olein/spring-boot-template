package com.jonak.template.service;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class FullTextEntityManagerFactory {

    @Autowired
    private EntityManager entityManager;

    public FullTextEntityManager getFullTextEntityManager() {
        return org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
    }

}
