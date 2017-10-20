package com.jonak.template.dao;

import com.jonak.template.entity.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

@Repository
@EnableAsync
public interface PersonDao extends CrudRepository<Person, Integer>{

    Person findFirstByName(String name);
}
