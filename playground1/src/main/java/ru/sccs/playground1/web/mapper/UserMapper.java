package ru.sccs.playground1.web.mapper;

import org.mapstruct.Mapper;
import ru.sccs.playground1.domain.user.User;
import ru.sccs.playground1.web.dto.user.UserDTO;

@Mapper
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO dto);

}
