package ok.sdacademy.mappers;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

  User toUser(UserDto userDto); // metoda może mieć dowolną nazwę

  UserDto toUserDto(User user);
}
