package pl.sdacademy.wiosnademo.model.mappers;

import org.mapstruct.Mapper;

import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.model.UserDto;

@Mapper
public interface UserMapper {

  UserDto toUserDto(User user);

  User toUser(UserDto userDto);
}
