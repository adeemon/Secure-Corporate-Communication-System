package ru.sccs.playground1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sccs.playground1.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}