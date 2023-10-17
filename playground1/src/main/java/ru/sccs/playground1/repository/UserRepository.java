package ru.sccs.playground1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sccs.playground1.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

}