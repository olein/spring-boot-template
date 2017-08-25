package com.jonak.template.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "address", catalog = "template")
public class Address implements java.io.Serializable {


    private Integer id;
    private String street;
    private int zip;
    private Set<Person> persons = new HashSet<Person>(0);

    public Address() {
    }


    public Address(String street, int zip) {
        this.street = street;
        this.zip = zip;
    }

    public Address(String street, int zip, Set<Person> persons) {
        this.street = street;
        this.zip = zip;
        this.persons = persons;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Column(name = "street", nullable = false, length = 10)
    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    @Column(name = "zip", nullable = false)
    public int getZip() {
        return this.zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    public Set<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}