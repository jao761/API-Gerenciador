package com.Joao.Gerenciador.services;

import com.Joao.Gerenciador.entities.Users;
import com.Joao.Gerenciador.exeptions.UserNotFoundException;
import com.Joao.Gerenciador.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> listarUsers() {
        try {
            List<Users> users = userRepository.findAll();

            if (users.isEmpty()) {
                throw new UserNotFoundException("Nenhum usuário encontrado.");
            }

            return users;
        } catch (UserNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Users> buscarPorId(Long id) {
        return userRepository.findById(id);
    }

    public String criarUser (Users users) {
        try {
            userRepository.save(users);
            return "Usuario salvo com sucesso";
        } catch (Exception e) {
            return "Erro ao salvar usuario " + e.getMessage();
        }
    }

    public String updateUser (Long id, String name, String email) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(name);
                    existingUser.setEmail(email);
                    userRepository.save(existingUser);
                    return "Usario Editado com Sucesso";
                })
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id " + id));
    }

    public String deleteUserForId (Long id) {
        if (usuarioExiste(id)) {
            userRepository.deleteById(id);
            return "Usuario apagado com sucesso";
        } else {
            throw new RuntimeException("Usuario nao encontrado");
        }
    }

    public boolean usuarioExiste(Long id) {
        return userRepository.existsById(id);
    }
}