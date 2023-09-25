package br.com.gabriel.api.resources.exceptions;

import br.com.gabriel.api.services.exceptions.DataIntegrityViolationException;
import br.com.gabriel.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String PERSON_NOT_FOUND = "Objeto não encontrado";
    public static final String EMAIL_ALREADY_REGISTERED = "Email já cadastrado!";
    @InjectMocks
    private ResourceExceptionHandler ExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustReturnAResponseEntityWithObjectNotFound_WhenTheErrorOccurs() {
        ResponseEntity<StandardError> response = ExceptionHandler.objectNotFound(
                new ObjectNotFoundException(PERSON_NOT_FOUND), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(PERSON_NOT_FOUND, response.getBody().getError());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
        assertNotEquals("/person/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void mustReturnAResponseEntityWithDataIntegrityViolationException_WhenTheErrorOccurs() {
        ResponseEntity<StandardError> response = ExceptionHandler.dataIntegrityViolationException(
                new DataIntegrityViolationException(EMAIL_ALREADY_REGISTERED), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(EMAIL_ALREADY_REGISTERED, response.getBody().getError());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
    }
}