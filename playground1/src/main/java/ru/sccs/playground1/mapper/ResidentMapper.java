package ru.sccs.playground1.mapper;

import org.mapstruct.Mapper;
import ru.sccs.playground1.dto.ResidentDTO;
import ru.sccs.playground1.entity.Resident;

@Mapper
public interface ResidentMapper {

    ResidentDTO dtoFromEntity(Resident resident);

    Resident entityFromDto(ResidentDTO residentDTO);
}
