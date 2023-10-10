package ru.sccs.playground1.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sccs.playground1.dto.ApartmentCreationDTO;
import ru.sccs.playground1.dto.ApartmentDTO;
import ru.sccs.playground1.entity.Apartment;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {

    Apartment entityFromDto(ApartmentDTO apartmentDTO);

    @Mapping(target = "residents", expression = "java(new LinkedHashSet())")
    Apartment entityFromCreationDto(ApartmentCreationDTO apartmentCreationDTO);

    ApartmentDTO dtoFromEntity(Apartment apartment);
}
