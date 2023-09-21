package br.com.gabriel.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @Column(unique = true)
    private String name;
    private String email;
    private String password;
}
