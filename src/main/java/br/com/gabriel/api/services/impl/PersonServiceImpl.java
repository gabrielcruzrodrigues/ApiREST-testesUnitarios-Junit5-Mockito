package br.com.gabriel.api.services.impl;

import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.repositories.PersonRepository;
import br.com.gabriel.api.services.PersonService;
import br.com.gabriel.api.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findById(Integer id) {
        Optional<Person> obj = personRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
