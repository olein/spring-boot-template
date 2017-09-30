package com.jonak.template.configuration;

import javax.persistence.EntityManager;

import com.jonak.template.service.HibernateSearchConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
public class HibernateSearchConfiguration {

    @Autowired
    private EntityManager entityManager;

    @Bean
    HibernateSearchConfigurationService hibernateSearchService() {
        HibernateSearchConfigurationService hibernateSearchService = new HibernateSearchConfigurationService(entityManager);
        hibernateSearchService.initializeHibernateSearch();
        return hibernateSearchService;
    }
}
