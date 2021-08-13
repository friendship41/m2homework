package com.friendship41.m2homework.main.exception;

import com.friendship41.m2homework.common.exception.BizException;
import com.friendship41.m2homework.main.data.entity.Homework;
import com.friendship41.m2homework.main.data.entity.M2Character;

public class CharacterHomeworkNotFoundException extends BizException {
  public CharacterHomeworkNotFoundException(final int m2CharacterNo, final int homeworkNo) {
    super("characterHomework not found, m2CharacterNo: " + m2CharacterNo + ", homeworkNo: " + homeworkNo);
  }

  public CharacterHomeworkNotFoundException(final M2Character m2Character, final Homework homework) {
    super("characterHomework not found, m2Character: " + m2Character + ", homework: " + homework);
  }
}
