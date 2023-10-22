package ru.sccs.playground1.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.sccs.playground1.web.dto.validation.OnCreate;
import ru.sccs.playground1.web.dto.validation.OnUpdate;

@Data
public class UserDTO {

    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(groups = {OnCreate.class})
    private String passwordConfirmation;

}
