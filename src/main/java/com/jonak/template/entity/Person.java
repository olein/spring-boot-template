package com.jonak.template.entity;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "person", catalog = "template")
public class Person implements java.io.Serializable {


    private Integer id;
    private Address address;
    private String name;
    private String mobile;

    public Person() {
    }

    public Person(Address address, String name, String mobile) {
        this.address = address;
        this.name = name;
        this.mobile = mobile;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "mobile", nullable = false, length = 20)
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}


