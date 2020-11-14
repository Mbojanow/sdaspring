package ok.sdacademy.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = GroupMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

  @Mapping(source = "nick", target = "username")
  @Mapping(source = "mail", target = "email")
  User toUser(UserDto userDto); // metoda może mieć dowolną nazwę

//  @Mapping(target = "nick", source = "username")
//  @Mapping(target = "mail", source = "email")
  @InheritInverseConfiguration
  UserDto toUserDto(User user);
}
