package com.jonak.template.service;

import com.jonak.template.data.PersonData;

import java.util.concurrent.CompletableFuture;

public interface PersonService {

    public CompletableFuture<Boolean> createPerson(PersonData personData);

}
