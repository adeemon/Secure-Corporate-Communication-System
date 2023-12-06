package ru.sccs.playground1.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.sccs.playground1.domain.user.Role;
import ru.sccs.playground1.domain.user.User;
import ru.sccs.playground1.web.dto.user.UserCreationDTO;
import ru.sccs.playground1.web.dto.user.UserDTO;

@Mapper
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO dto);

    @Mapping(source = ".", target = "role", qualifiedByName = "defaultRole")
    User toEntity(UserCreationDTO dto);

    @Named("defaultRole")
    default Role defaultRole(UserCreationDTO dto) {
        return Role.ROLE_USER;
    }

}
