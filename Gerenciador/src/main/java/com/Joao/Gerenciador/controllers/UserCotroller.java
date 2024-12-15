package com.Joao.Gerenciador.controllers;

import com.Joao.Gerenciador.entities.Users;
import com.Joao.Gerenciador.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastro_user")
public class UserCotroller {

    private final UserService userService;

    public UserCotroller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAll() {
            return ResponseEntity.ok(userService.listarUsers());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody Users users, BindingResult result) {
        System.out.println("Objeto Users recebido: " + users);

        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Erro de validação: ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }

        String mensagem = userService.criarUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }


}
