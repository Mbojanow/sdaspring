package pl.sdacademy.wiosnademo.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.model.UserDto;

@Mapper
public interface UserMapper {

  UserDto toUserDto(User user);

  @Mapping(target = "password", ignore = true)
  User toUser(UserDto userDto);
}
