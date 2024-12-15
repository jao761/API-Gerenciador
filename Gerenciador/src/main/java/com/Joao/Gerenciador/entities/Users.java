package com.Joao.Gerenciador.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não deve estar vazio")
    private String name;

    @NotBlank(message = "O email não deve estar vazio")
    private String email;

    @NotBlank(message = "A senha não deve estar vazia")
    private String password;


}
