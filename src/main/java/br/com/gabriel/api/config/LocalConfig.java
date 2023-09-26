package br.com.gabriel.api.config;

import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Slf4j
@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private PersonRepository personRepository;

    @Bean
    public void startDB() {
        Person person1 = new Person(null, "Gabriel", "gabriel@gmail.com", "123456789");
        Person person2 = new Person(null, "Samuel", "samuel@gmail.com", "123456789");
        Person person3 = new Person(null, "Stéfany", "stefany@gmail.com", "123456789");
        Person person4 = new Person(null, "Jurema", "jurema@gmail.com", "123456789");
        Person person5 = new Person(null, "Pedro", "pedro@gmail.com", "123456789");

        personRepository.saveAll(List.of(person1, person2, person3, person4, person5));
    }

}
