package ru.sccs.playground1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sccs.playground1.entity.json.ApartmentData;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentCreationDTO {
    private ApartmentData apartmentData;
}
