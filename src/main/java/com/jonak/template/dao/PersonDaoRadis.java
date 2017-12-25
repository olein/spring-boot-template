package com.jonak.template.dao;

import com.jonak.template.entity.Person;

public interface PersonDaoRadis {

    public void addPerson(Person person);

    public Person getPersonAtIndex(String name);

    public long getNumberOfPerson();

    public void removePerson(String name);
}
