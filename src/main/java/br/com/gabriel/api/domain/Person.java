package br.com.gabriel.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Column(nullable = true)
    @Size(min = 3, max = 60)
    private String name;

    @NotNull
    @NotBlank
    @Column(unique = true)
    @Size(min = 10, max = 60)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 20)
    @Column(nullable = true)
    private String password;
}
