package com.jonak.template.dao;

import com.jonak.template.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;


@Transactional
@Component
public class PersonDaoRadisImpl implements PersonDaoRadis {

    private static Logger log = Logger.getLogger(PersonDaoRadisImpl.class.getName());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    private static final String KEY = "person";

    @Override
    public void addPerson(Person person) {
        log.info("Person add dao start");
        hashOperations.put(KEY, person.getName(), person);
        //hashOps.putIfAbsent(KEY, person.getId(), person);
        log.info("In person add dao end");
    }

    @Override
    public long getNumberOfPerson() {
        return hashOperations.size(KEY);
    }

    @Override
    public Person getPersonAtIndex(String name) {
        System.out.println(hashOperations.getOperations().boundHashOps(KEY));
        return (Person) hashOperations.get(KEY, name);
    }

    @Override
    public void removePerson(String name) {
        hashOperations.delete(KEY, name);
    }

}
