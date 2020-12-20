package pl.sdacademy.wiosnademo.model.mappers;

import org.mapstruct.Mapper;

import pl.sdacademy.wiosnademo.domain.Group;
import pl.sdacademy.wiosnademo.model.GroupDto;

@Mapper
public interface GroupMapper {
  Group toGroup(GroupDto groupDto);
  GroupDto toGroupDto(Group group);
}
