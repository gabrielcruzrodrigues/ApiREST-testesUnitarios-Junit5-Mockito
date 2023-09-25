package br.com.gabriel.api.services.impl;

import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.domain.dto.PersonDTO;
import br.com.gabriel.api.repositories.PersonRepository;
import br.com.gabriel.api.services.exceptions.DataIntegrityViolationException;
import br.com.gabriel.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Gabriel";
    public static final String EMAIL = "gabriel@gmail.com";
    public static final String PASSWORD = "123";
    
    public static final String PERSON_NOT_FOUND = "Pessoa não encontrada!";
    public static final int INDEX = 0;
    public static final String INVALID_DATA = "Dados invalidos";

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ModelMapper modelMapper;

    private Person person;
    private PersonDTO personDTO;
    private Optional<Person> personOptional;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startPerson();
    }

    @Test
    void shouldAnPersonInstance_whenToCallFindById() {
        when(personRepository.findById(anyInt())).thenReturn(personOptional);

        Person response = personService.findById(ID);

        assertNotNull(response);
        assertEquals(Person.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void shouldAnOnObjectNotFoundException_whenToCallFindById() {
        when(personRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(PERSON_NOT_FOUND));

        try {
            personService.findById(ID);
        } catch(Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(PERSON_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void shouldAnListOfPerson_whenToCallFindAll() {
        when(personRepository.findAll()).thenReturn(List.of(person));

        List<Person> response = personService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(person.getClass(), response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void shouldAnSuccess_whenToCallCreate() {
        when(personRepository.save(any())).thenReturn(person);

        Person response = personService.create(personDTO);


        assertNotNull(response);
        assertEquals(person.getClass(), response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void shouldAnDataIntegrityViolationException_whenToCallCreate() {
        when(personRepository.findByEmail(anyString())).thenReturn(personOptional);

        try {
            personOptional.get().setId(2);
            personService.create(personDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("Email já cadastrado no sistema", ex.getMessage());
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByEmail() {
    }

    private void startPerson() {
        person = new Person(ID, NAME, EMAIL, PASSWORD);
        personDTO = new PersonDTO(ID, NAME, EMAIL, PASSWORD);
        personOptional = Optional.of(new Person(ID, NAME, EMAIL, PASSWORD));
    }

}
