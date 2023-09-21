package br.com.gabriel.api.config;

import br.com.gabriel.api.domain.Person;
import br.com.gabriel.api.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private PersonRepository personRepository;

    @Bean
    public void startDB() {
        Person person1 = new Person(null, "Gabriel", "gabriel@gmail.com", "123");
        Person person2 = new Person(null, "Samuel", "samuel@gmail.com", "123");

        personRepository.saveAll(List.of(person1, person2));
    }

}
