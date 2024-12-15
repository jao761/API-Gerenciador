package com.Joao.Gerenciador.services;

import com.Joao.Gerenciador.entities.Users;
import com.Joao.Gerenciador.exeptions.UserNotFoundException;
import com.Joao.Gerenciador.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String criarUser (Users users) {
        try {
            userRepository.save(users);
            return "Usuario salvo com sucesso";
        } catch (Exception e) {
            return "Erro ao salvar usuario " + e.getMessage();
        }
    }
}
