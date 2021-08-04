package com.friendship41.m2homework.main.repository;

import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.type.JobType;
import java.util.List;

public interface M2CharacterRepositoryCustom {
  List<M2Character> findByDynamicQuery(Integer memberNo, String characterName, JobType jobType, Boolean isMain,
      Integer characterImageNo);
}
