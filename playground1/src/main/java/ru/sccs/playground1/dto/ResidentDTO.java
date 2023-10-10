package ru.sccs.playground1.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ResidentDTO(
//        Long residentId,
        String firstName,
        String lastName,
        LocalDate birthDate
){}