package com.friendship41.m2homework.main.data.type;

public enum JobType {
  BERSERKER("berserker", "버서커"),
  KNIGHT("knight", "나이트"),
  WIZARD("wizard", "위자드"),
  PRIEST("priest", "프리스트"),
  RANGER("ranger", "레인저"),
  HEAVY_GUNNER("heavyGunner", "헤비거너"),
  THIEF("thief", "시프"),
  ASSASSIN("assassin", "어쌔신"),
  RUNE_BLADER("runeBlader", "룬블레이더"),
  STRIKER("striker", "스트라이커"),
  SOUL_BINDER("soulBinder", "소울바인더");

  private final String enName;
  private final String korName;

  JobType(final String enName, final String korName) {
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
