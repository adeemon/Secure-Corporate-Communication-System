package ru.sccs.playground1.dto;

import lombok.Builder;
import lombok.Data;
import ru.sccs.playground1.entity.json.ApartmentData;

import java.util.Set;

@Data
@Builder
public class ApartmentDTO {
    //        Long apartmentId,
    private Set<ResidentDTO> residents;
    private ApartmentData apartmentData;
}
