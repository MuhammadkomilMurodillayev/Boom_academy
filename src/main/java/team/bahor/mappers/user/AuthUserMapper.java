package team.bahor.mappers.user;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import team.bahor.dto.user.UserCreateDto;
import team.bahor.dto.user.UserDto;
import team.bahor.dto.user.UserUpdateDto;
import team.bahor.entity.user.AuthUser;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends AbstractMapper<AuthUser, UserDto, UserCreateDto, UserUpdateDto> {
    @Override
    UserDto toDto(AuthUser entity);

    @Override
    List<UserDto> toDto(List<AuthUser> entities);

    @Override
    AuthUser fromCreateDto(UserCreateDto createDto);

    @Override
    AuthUser fromUpdateDto(UserUpdateDto updateDto);
}
