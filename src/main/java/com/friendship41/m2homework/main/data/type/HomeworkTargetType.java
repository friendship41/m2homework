package com.friendship41.m2homework.main.data.type;

public enum HomeworkTargetType {
  ACCOUNT("account", "계정"),
  CHARACTER("character", "캐릭터");

  private final String enName;
  private final String korName;

  HomeworkTargetType(final String enName, final String korName) {
    this.enName = enName;
    this.korName = korName;
  }

  public String getEnName() {
    return enName;
  }

  public String getKorName() {
    return korName;
  }
}
