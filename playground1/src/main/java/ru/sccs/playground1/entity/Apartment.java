package ru.sccs.playground1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.sccs.playground1.entity.json.ApartmentData;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "apartment")
public class Apartment {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id", nullable = false)
    Long apartmentId;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "apartment_apartment_id")
    private Set<Resident> residents;

    @Column(name = "apartment_data", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private ApartmentData apartmentData;
}