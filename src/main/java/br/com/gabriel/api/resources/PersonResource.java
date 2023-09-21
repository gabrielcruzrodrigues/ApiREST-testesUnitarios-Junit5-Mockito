package br.com.gabriel.api.resources;

import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/person")
public class PersonResource {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(personService.findById(id));
    }

}
