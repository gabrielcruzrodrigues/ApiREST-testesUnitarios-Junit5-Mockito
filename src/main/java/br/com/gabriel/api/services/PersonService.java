package br.com.gabriel.api.services;

import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.domain.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    Person findById(Integer id);
    List<Person> findAll();
    Person create(PersonDTO obj);
    Person update(PersonDTO obj);
}
