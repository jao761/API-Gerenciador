package com.Joao.Gerenciador.repositories;

import com.Joao.Gerenciador.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
