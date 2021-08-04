package com.friendship41.m2homework.main.repository;

import static com.friendship41.m2homework.main.data.entity.QM2Character.m2Character;
import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.type.JobType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
public class M2CharacterRepositoryImpl implements M2CharacterRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  @Override
  public List<M2Character> findByDynamicQuery(final Integer memberNo, final String characterName, final JobType jobType,
      final Boolean isMain, final Integer characterImageNo) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    if (memberNo != null) {
      booleanBuilder.and(m2Character.memberNo.eq(memberNo));
    }
    if (characterName != null) {
      booleanBuilder.and(m2Character.characterName.eq(characterName));
    }
    if (jobType != null) {
      booleanBuilder.and(m2Character.jobType.eq(jobType));
    }
    if (isMain != null) {
      booleanBuilder.and(m2Character.isMain.eq(isMain));
    }
    if (characterImageNo != null) {
      booleanBuilder.and(m2Character.characterImageNo.eq(characterImageNo));
    }

    return this.queryFactory
        .selectFrom(m2Character)
        .where(booleanBuilder)
        .fetch();
  }
}
