package com.jonak.template.endpoint;

import com.jonak.template.data.PersonData;
import com.jonak.template.req_res.CreatePersonRequest;
import com.jonak.template.req_res.CreatePersonResponse;
import com.jonak.template.req_res.GetPersonResponse;
import com.jonak.template.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@RestController
@EnableAsync
public class PersonEndpoint {

    private static Logger log = Logger.getLogger(PersonEndpoint.class.getName());

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/createPerson", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public @ResponseBody
    CreatePersonResponse createContact(@RequestBody CreatePersonRequest request) {
        CreatePersonResponse response = new CreatePersonResponse();
        if (request == null) {
            response.setResult(false);
        }

        if (request.getName() != null && request.getMobile() != null) {
            CompletableFuture<Boolean> result = personService.createPerson(new PersonData(request.getName(), request.getMobile(), request.getStreet(), request.getZip()));
            try {
                log.info("Endpoint start");
                if (result.get()) {
                    response.setResult(Boolean.TRUE);
                } else {
                    response.setResult(Boolean.FALSE);
                }
            } catch (InterruptedException ex) {
                response.setResult(Boolean.FALSE);
            } catch (ExecutionException ex) {
                response.setResult(Boolean.FALSE);
            }
        }
        return response;
    }

    @RequestMapping(value = "/getPersonById", method = RequestMethod.GET)
    public @ResponseBody
    GetPersonResponse getContact(@PathParam("personId") int personId) {
        GetPersonResponse response = new GetPersonResponse();

        long start = System.currentTimeMillis();

        if (personId > 0) {
            try {
                CompletableFuture<PersonData> result = personService.getPerson(new PersonData().withPersonId(personId));
                if (result.get() != null) {
                    response.setMobile(result.get().getMobile());
                    response.setName(result.get().getName());
                    response.setStreet(result.get().getStreet());
                    response.setResult(Boolean.TRUE);
                } else {
                    response.setResult(false);
                }
            } catch (InterruptedException ex) {
                response.setResult(Boolean.FALSE);
            } catch (ExecutionException ex) {
                response.setResult(Boolean.FALSE);
            } catch (Exception ex) {
                response.setResult(Boolean.FALSE);
            }
        } else {
            response.setResult(Boolean.FALSE);
        }
        long end = System.currentTimeMillis();
        log.info("Task completed in " + (end - start) + " ms");
        return response;
    }
}
