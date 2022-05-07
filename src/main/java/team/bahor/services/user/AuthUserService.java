package team.bahor.services.user;

import team.bahor.dto.user.UserCreateDto;
import team.bahor.dto.user.UserDto;
import team.bahor.dto.user.UserUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface AuthUserService extends GenericCrudService<
        UserDto,
        UserCreateDto,
        UserUpdateDto,
        String
        > {

    }