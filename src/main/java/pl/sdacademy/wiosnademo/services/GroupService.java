package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.Group;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.repositories.GroupRepository;

@Service
@Transactional
public class GroupService {

  private final GroupRepository groupRepository;
  private final UserCrudService userCrudService;

  public GroupService(final GroupRepository groupRepository, final UserCrudService userCrudService) {
    this.groupRepository = groupRepository;
    this.userCrudService = userCrudService;
  }

  public Group createGroup(Group group) {
    groupRepository.findById(group.getName())
        .ifPresent(existingGroup -> { throw new ApplicationException("Group already exists"); } );
    return groupRepository.save(group);
  }

  public void addUserToGroup(String groupName, String username) {
    final Group group = groupRepository.findByGroupNameWithUsers(groupName)
        .orElseThrow(() -> new ApplicationException("Requested group does not exist"));
    final User user = userCrudService.getUserByUsername(username);
    final boolean userAlreadyGroupMember = group.getUsers().stream()
        .anyMatch(usr -> usr.getUsername().equals(username));
    if (userAlreadyGroupMember) {
      throw new ApplicationException("User already member of requested group");
    }
    group.getUsers().add(user);
  }
}
