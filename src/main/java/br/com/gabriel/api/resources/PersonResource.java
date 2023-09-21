package br.com.gabriel.api.resources;

import br.com.gabriel.api.config.ModelMapperConfig;
import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.domain.dto.PersonDTO;
import br.com.gabriel.api.services.PersonService;
//import br.com.gabriel.api.services.impl.PersonServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/person")
public class PersonResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(personService.findById(id), PersonDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok().body(personService.findAll()
                .stream().map(x -> mapper.map(x, PersonDTO.class)).collect(Collectors.toList()));
    }
}
