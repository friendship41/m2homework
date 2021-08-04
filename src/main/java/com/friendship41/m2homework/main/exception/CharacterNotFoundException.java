package com.friendship41.m2homework.main.exception;

import com.friendship41.m2homework.common.exception.BizException;
import com.friendship41.m2homework.main.data.entity.M2Character;

public class CharacterNotFoundException extends BizException {
  public CharacterNotFoundException(final int m2CharacterNo) {
    super("character not found, m2CharacterNo: " + m2CharacterNo);
  }

  public CharacterNotFoundException(final M2Character m2Character) {
    super("character not found, m2Character: " + m2Character);
  }
}
