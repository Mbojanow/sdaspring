package pl.sdacademy.wiosnademo.model.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.model.UserDto;

@Mapper(uses = GroupMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

  @InheritInverseConfiguration
  UserDto toUserDto(User user);

  @Mapping(target = "password", ignore = true)
  @Mapping(target = "username", source = "nick")
  @Mapping(target = "email", source = "mail")
  User toUser(UserDto userDto);
}
