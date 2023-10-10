package ru.sccs.playground1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sccs.playground1.entity.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}