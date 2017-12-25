package com.jonak.template.service;

import com.jonak.template.data.PersonData;

import java.util.concurrent.CompletableFuture;

public interface PersonRadisService {

    public CompletableFuture<Boolean> createPerson(PersonData personData);

    public CompletableFuture<PersonData> getPerson(PersonData personData) throws Exception;

    public CompletableFuture<Boolean> removePerson(String name) throws Exception;
}
