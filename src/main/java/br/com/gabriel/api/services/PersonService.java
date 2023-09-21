package br.com.gabriel.api.services;

import br.com.gabriel.api.domain.Person;

public interface PersonService {

    Person findById(Integer id);
}
