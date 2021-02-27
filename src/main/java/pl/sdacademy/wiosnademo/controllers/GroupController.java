package pl.sdacademy.wiosnademo.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.Group;
import pl.sdacademy.wiosnademo.services.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

  private final GroupService groupService;

  public GroupController(final GroupService groupService) {
    this.groupService = groupService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Group createGroup(@Valid @RequestBody Group group) {
    return groupService.createGroup(group);
  }

  // POST /api/groups/{group_name}/users/{username}
  @PostMapping("/{group_name}/users/{username}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void addUserToGroup(@PathVariable("group_name") String groupName, @PathVariable("username") String username) {
    groupService.addUserToGroup(groupName, username);
  }

}
