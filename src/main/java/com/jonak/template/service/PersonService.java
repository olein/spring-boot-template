package com.jonak.template.service;

import com.jonak.template.data.PersonData;
import com.jonak.template.req_res.GetPersonResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.concurrent.CompletableFuture;

public interface PersonService {

    public CompletableFuture<Boolean> createPerson(PersonData personData);

    @Retryable(maxAttempts = 3, backoff=@Backoff(delay = 1000))
    public CompletableFuture<PersonData> getPerson(PersonData personData) throws Exception;

}
