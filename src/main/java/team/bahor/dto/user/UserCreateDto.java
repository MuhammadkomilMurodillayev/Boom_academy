package team.bahor.dto.user;


import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.BaseGenericDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCreateDto implements BaseGenericDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String phone;

}

