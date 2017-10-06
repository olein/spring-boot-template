package com.jonak.template.service;

import com.jonak.template.dao.AddressDao;
import com.jonak.template.dao.PersonDao;
import com.jonak.template.data.PersonData;
import com.jonak.template.entity.Address;
import com.jonak.template.entity.Person;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
public class PersonServiceImpl implements PersonService {

    private static Logger log = Logger.getLogger(PersonServiceImpl.class.getName());

    @Autowired
    private PersonDao personDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    LuceneQueryFactory luceneQueryFactory;

    @Autowired
    private FullTextEntityManagerFactory factory;

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
    @Override
    public CompletableFuture<PersonData> getPerson(PersonData personData) throws Exception {
        Person person = personDao.findOne(personData.getPersonId());
        Address address = addressDao.findOne(person.getAddress().getId());
        PersonData data = new PersonData();
        log.info(person.getMobile() + " " + address.getStreet());
        if (person == null || address == null) {
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


    /*
    * Lucene integrated
    * get the factory and query builder if needed
    * basic and term query is exact match
    * wild card is substring match
    * Boolean query is combination of queries. and or not applicable.
    * */
    @Async
    @Override
    @Transactional
    public CompletableFuture<PersonData> getPersonByName(String name) {

        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = factory.getFullTextEntityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder = luceneQueryFactory.getQueryBuilder(fullTextEntityManager, Person.class);

        Query basicQuery = luceneQueryFactory.createBasicQuery(queryBuilder, name);

        Query wildcardQuery = luceneQueryFactory.createWildCardQuery("name", name);

        Query termQuery = luceneQueryFactory.createTermQuery("name", name);

        List<BooleanClause> list = new ArrayList<BooleanClause>();
        list.add(new BooleanClause(basicQuery, BooleanClause.Occur.SHOULD));
        list.add(new BooleanClause(wildcardQuery, BooleanClause.Occur.SHOULD));
        list.add(new BooleanClause(termQuery, BooleanClause.Occur.SHOULD));
        BooleanQuery query = luceneQueryFactory.createBooleanQuery(list);

        // wrap Lucene query in an Hibernate Query object
        org.hibernate.search.jpa.FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(query, Person.class);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<Person> results = jpaQuery.getResultList();
        log.info("size " + results.size());
        PersonData data = new PersonData();
        data.setName(results.get(0).getName());
        data.setMobile(results.get(0).getMobile());
        data.setStreet(results.get(0).getAddress().getStreet());

        return CompletableFuture.completedFuture(data);
    }


}
