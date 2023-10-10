package ru.sccs.playground1.entity.json;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentData {
    private Integer number;
    private Integer floor;
    private Integer space;
}
