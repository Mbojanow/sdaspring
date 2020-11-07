package pl.sdacademy.demo.entities;

import lombok.Getter;

@Getter
public enum UserType {
  NORMAL("user.type.normal"),
  SPECIAL("user.type.special");

  private final String messageKey;

  UserType(final String messageKey) {
    this.messageKey = messageKey;
  }
}
