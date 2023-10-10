package ru.sccs.playground1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sccs.playground1.entity.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
}