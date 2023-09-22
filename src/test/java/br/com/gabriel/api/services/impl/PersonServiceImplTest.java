package br.com.gabriel.api.services.impl;

import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.domain.dto.PersonDTO;
import br.com.gabriel.api.repositories.PersonRepository;
import br.com.gabriel.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Gabriel";
    public static final String EMAIL = "gabriel@gmail.com";
    public static final String PASSWORD = "123";

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
        when(personRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Pessoa não encontrada!"));

        try {
            personRepository.findById(ID);
        } catch(Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Pessoa não encontrada!", ex.getMessage());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
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
