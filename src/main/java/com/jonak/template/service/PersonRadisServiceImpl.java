package com.jonak.template.service;

import com.jonak.template.dao.PersonDaoRadis;
import com.jonak.template.dao.PersonDaoRadisImpl;
import com.jonak.template.data.PersonData;
import com.jonak.template.entity.Address;
import com.jonak.template.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
public class PersonRadisServiceImpl implements PersonRadisService {

    private static Logger log = Logger.getLogger(PersonRadisServiceImpl.class.getName());

    @Autowired
    private PersonDaoRadis personDaoRadis;

    @Async
    @Override
    @Transactional
    public CompletableFuture<Boolean> createPerson(PersonData personData) {

        Address address = new Address(personData.getStreet(), personData.getZip());

        log.info("In radis service");
        Person person = new Person();
        person.setId(1);
        person.setName(personData.getName());
        person.setMobile(personData.getMobile());
        person.setAddress(address);
        try {
            personDaoRadis.addPerson(person);
            log.info("data saved");
        } catch (Exception e) {
            log.info("Error : " + e.getMessage());
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    @Override
    public CompletableFuture<PersonData> getPerson(PersonData personData) throws Exception {

        Person person = personDaoRadis.getPersonAtIndex(personData.getName());

        log.info("Total radis size : " + personDaoRadis.getNumberOfPerson());
        PersonData data = new PersonData();

        if (person == null) {
            data.setResult(false);
            return CompletableFuture.completedFuture(data);
        }
        data.setName(person.getName());
        data.setMobile(person.getMobile());
        data.setStreet(person.getAddress().getStreet());
        data.setResult(true);

        return CompletableFuture.completedFuture(data);
    }

    @Override
    public CompletableFuture<Boolean> removePerson(String name) throws Exception {

        try {
            personDaoRadis.removePerson(name);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        log.info("Total radis size : " + personDaoRadis.getNumberOfPerson());

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
