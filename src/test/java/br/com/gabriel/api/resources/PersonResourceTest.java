package br.com.gabriel.api.resources;

import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.domain.dto.PersonDTO;
import br.com.gabriel.api.services.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Gabriel";
    public static final String EMAIL = "gabriel@gmail.com";
    public static final String PASSWORD = "123";

    private Person person;
    private PersonDTO personDTO;

    @InjectMocks
    private PersonResource personResource;

    @Mock
    private PersonServiceImpl personService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPerson();
    }

    @Test
    void shouldAnSuccess_whenToCallFindById() {
        when(personService.findById(anyInt())).thenReturn(person);
        when(modelMapper.map(any(), any())).thenReturn(personDTO);

        ResponseEntity<PersonDTO> response = personResource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PersonDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
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

    private void startPerson() {
        person = new Person(ID, NAME, EMAIL, PASSWORD);
        personDTO = new PersonDTO(ID, NAME, EMAIL, PASSWORD);
    }
}