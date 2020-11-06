package pl.sdacademy.wiosnademo;

import lombok.Getter;

@Getter
public enum UserType {
  NORMAL("user.type.normal"),
  SPECIAL("user.type.special");

  private final String readableName;

  UserType(String readableName) {
    this.readableName = readableName;
  }
}
