package com.jonak.template.endpoint;

import com.jonak.template.data.PersonData;
import com.jonak.template.req_res.CreatePersonRequest;
import com.jonak.template.req_res.CreatePersonResponse;
import com.jonak.template.req_res.GetPersonResponse;
import com.jonak.template.service.PersonRadisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@RestController
@EnableAsync
public class PersonRadisEndpoint {

    private static Logger log = Logger.getLogger(PersonRadisEndpoint.class.getName());

    @Autowired
    private PersonRadisService personRadisService;

    @RequestMapping(value = "/createPersonRadis", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public @ResponseBody
    CreatePersonResponse createContact(@RequestBody CreatePersonRequest request) {
        CreatePersonResponse response = new CreatePersonResponse();
        if (request == null) {
            response.setResult(false);
        }
        log.info("Endpoint start");
        if (request.getName() != null && request.getMobile() != null) {
            CompletableFuture<Boolean> result = personRadisService.createPerson(new PersonData(request.getName(), request.getMobile(), request.getStreet(), request.getZip()));
            try {
                if (result.get() != null) {
                    response.setResult(result.get());
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

    @RequestMapping(value = "/getPersonByNameRadis", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    GetPersonResponse getContact(@PathParam("name") String name) {
        GetPersonResponse response = new GetPersonResponse();

        long start = System.currentTimeMillis();

        if (name != null) {
            try {
                CompletableFuture<PersonData> result = personRadisService.getPerson(new PersonData().withName(name));
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

    @RequestMapping(value = "/removePersonByNameRadis", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    GetPersonResponse removeContact(@PathParam("name") String name) {
        GetPersonResponse response = new GetPersonResponse();

        long start = System.currentTimeMillis();

        if (name != null) {
            try {
                CompletableFuture<Boolean> result = personRadisService.removePerson(name);
                if (result.get() != null) {
                    response.setResult(result.get());
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
