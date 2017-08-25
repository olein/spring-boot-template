package com.jonak.template.service;

import com.jonak.template.dao.AddressDao;
import com.jonak.template.dao.PersonDao;
import com.jonak.template.data.PersonData;
import com.jonak.template.entity.Address;
import com.jonak.template.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Component
public class PersonServiceImpl implements PersonService{

    private static Logger log =  Logger.getLogger(PersonServiceImpl.class.getName());

    @Autowired
    private PersonDao personDao;

    @Autowired
    private AddressDao addressDao;

    @Async
    @Override
    @Transactional
    public CompletableFuture<Boolean> createPerson(PersonData personData) {

        Address address = new Address(personData.getStreet(), personData.getZip());
        addressDao.save(address);

        Person person = new Person();
        person.setName(personData.getName());
        person.setMobile(personData.getMobile());
        person.setAddress(address);
        personDao.save(person);
        log.info("data saved");

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
