package team.bahor.dto.user;

import lombok.Getter;
import lombok.Setter;
import team.bahor.dto.GenericDto;

@Getter
@Setter
public class UserDto extends GenericDto {
    private String fullName;

    private String username;

    private String password;

    private String role_id;

    private Double balance;

    private String photo_url;

    private String email;

    private String phone;
}

