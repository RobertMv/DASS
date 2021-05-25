package com.example.PAPS.repositories;

import com.example.PAPS.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmployee_Email(String email);
}
