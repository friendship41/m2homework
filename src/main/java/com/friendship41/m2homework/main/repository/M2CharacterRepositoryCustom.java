package com.friendship41.m2homework.main.repository;

import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.type.JobType;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface M2CharacterRepositoryCustom {
  Page<M2Character> findByDynamicQuery(Integer memberNo, String characterName, JobType jobType, Boolean isMain,
      Integer characterImageNo, Pageable pageable);
}
