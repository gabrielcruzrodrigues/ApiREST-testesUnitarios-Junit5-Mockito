package br.com.gabriel.api.services;

import br.com.gabriel.api.domain.Person;

import java.util.List;

public interface PersonService {

    Person findById(Integer id);

    List<Person> findAll();
}
