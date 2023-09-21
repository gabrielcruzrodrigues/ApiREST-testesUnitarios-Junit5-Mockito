package br.com.gabriel.api.services.impl;

import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.repositories.PersonRepository;
import br.com.gabriel.api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findById(Integer id) {
        Optional<Person> obj = personRepository.findById(id);
        return obj.orElse(null);
    }
}
