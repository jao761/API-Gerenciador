package com.Joao.Gerenciador.controllers;

import com.Joao.Gerenciador.entities.Users;
import com.Joao.Gerenciador.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserCotroller {

    private final UserService userService;

    public UserCotroller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/ver-usuarios")
    public ResponseEntity<List<Users>> getAll() {
            return ResponseEntity.ok(userService.listarUsers());
    }

    @GetMapping("/ver-usuarios/{id}")
    public ResponseEntity<Users> getById(@PathVariable Long id) {
        return userService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
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

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> updateData(@Valid @PathVariable Long id, @RequestBody Users users) {
        try {
            String message = userService.updateUser(id, users.getName(), users.getEmail());
            return ResponseEntity.ok(message);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
