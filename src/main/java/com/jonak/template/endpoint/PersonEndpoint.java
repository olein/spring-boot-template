package com.jonak.template.endpoint;

import com.jonak.template.data.PersonData;
import com.jonak.template.req_res.CreatePersonRequest;
import com.jonak.template.req_res.CreatePersonResponse;
import com.jonak.template.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@RestController
@EnableAsync
public class PersonEndpoint {

    private static Logger log =  Logger.getLogger(PersonEndpoint.class.getName());

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

    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody
    String createContact() {
        return "template project 1.0.13";
    }
}
