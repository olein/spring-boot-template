package com.jonak.template.service;

import com.jonak.template.dao.AddressDao;
import com.jonak.template.dao.PersonDao;
import com.jonak.template.data.PersonData;
import com.jonak.template.entity.Address;
import com.jonak.template.entity.Person;
//import org.hibernate.search.jpa.FullTextEntityManager;
//import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
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

    @Async
    //@Retryable( maxAttempts = 2, backoff=@Backoff(delay = 1000))
    @Override
    public CompletableFuture<PersonData> getPerson(PersonData personData) throws Exception {
        Person person = personDao.findOne(personData.getPersonId());
        Address address = addressDao.findOne(person.getAddress().getId());
        PersonData data = new PersonData();
        log.info(person.getMobile()+ " "+ address.getStreet());
        if(person==null || address==null) {
            data.setResult(false);
            return CompletableFuture.completedFuture(data);
        }
        data.setName(person.getName());
        data.setMobile(person.getMobile());
        data.setStreet(address.getStreet());
        data.setResult(true);

        //for testing retry
//        log.info("Throwing exception");
//        test();
        return CompletableFuture.completedFuture(data);
    }

    private void test() throws Exception {
        log.info("in test");
        throw new RuntimeException();
    }

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public List search(String text) {
//
//        // get the full text entity manager
//        FullTextEntityManager fullTextEntityManager =
//                org.hibernate.search.jpa.Search.
//                        getFullTextEntityManager(entityManager);
//
//        // create the query using Hibernate Search query DSL
//        QueryBuilder queryBuilder =
//                fullTextEntityManager.getSearchFactory()
//                        .buildQueryBuilder().forEntity(Person.class).get();
//
//        // a very basic query by keywords
//        org.apache.lucene.search.Query query =
//                queryBuilder
//                        .keyword()
//                        .onFields("id", "name", "mobile")
//                        .matching(text)
//                        .createQuery();
//
//        // wrap Lucene query in an Hibernate Query object
//        org.hibernate.search.jpa.FullTextQuery jpaQuery =
//                fullTextEntityManager.createFullTextQuery(query, Person.class);
//
//        // execute search and return results (sorted by relevance as default)
//        @SuppressWarnings("unchecked")
//        List results = jpaQuery.getResultList();
//
//        return results;
//    } // method search
}
