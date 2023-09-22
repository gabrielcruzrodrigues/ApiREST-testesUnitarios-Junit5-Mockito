package br.com.gabriel.api.services.impl;

import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.domain.dto.PersonDTO;
import br.com.gabriel.api.repositories.PersonRepository;
import br.com.gabriel.api.services.PersonService;
import br.com.gabriel.api.services.exceptions.DataIntegrityViolationException;
import br.com.gabriel.api.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Person findById(Integer id) {
        Optional<Person> obj = personRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person create(PersonDTO obj) {
        findByEmail(obj);
        return personRepository.save(mapper.map(obj, Person.class));
    }

    private void findByEmail(PersonDTO obj) {
        Optional<Person> person = personRepository.findByEmail(obj.getEmail());
        if (person.isPresent()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
