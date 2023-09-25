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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Gabriel";
    public static final String EMAIL = "gabriel@gmail.com";
    public static final String PASSWORD = "123";

    public static final int INDEX = 0;

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
    void shouldAnSuccess_whenToCallFindAll() {
        when(personService.findAll()).thenReturn(List.of(person));
        when(modelMapper.map(any(), any())).thenReturn(personDTO);

        ResponseEntity<List<PersonDTO>> response = personResource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(personDTO.getClass(), response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
    }

    @Test
    void shouldAnSuccess_whenToCallCreate() {
        when(personService.create(any())).thenReturn(person);

        ResponseEntity<PersonDTO> response = personResource.create(personDTO);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void shouldAnSuccessAndPersonInstance_whenToCallCreate() {
        when(personService.update(personDTO)).thenReturn(person);
        when(modelMapper.map(any(), any())).thenReturn(personDTO);

        ResponseEntity<PersonDTO> response = personResource.update(personDTO, ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(personDTO.getClass(), response.getBody().getClass());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
    }

    @Test
    void shouldAnSuccess_whenToCallDelete() {
        doNothing().when(personService).delete(anyInt());

        ResponseEntity<PersonDTO> response = personResource.delete(ID);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        verify(personService, times(1)).delete(anyInt());
    }

    private void startPerson() {
        person = new Person(ID, NAME, EMAIL, PASSWORD);
        personDTO = new PersonDTO(ID, NAME, EMAIL, PASSWORD);
    }
}