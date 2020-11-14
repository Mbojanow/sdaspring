package ok.sdacademy.mappers;

import org.mapstruct.Mapper;

@Mapper
public interface GroupMapper {

  Group toGroup(GroupDto groupDto);
  GroupDto toGroupDto(Group group);
}
