package com.friendship41.m2homework.main.repository;

import static com.friendship41.m2homework.main.data.entity.QM2Character.m2Character;
import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.type.JobType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
public class M2CharacterRepositoryImpl implements M2CharacterRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  @Override
  public Page<M2Character> findByDynamicQuery(final Integer memberNo, final String characterName, final JobType jobType,
      final Boolean isMain, final Integer characterImageNo, final Pageable pageable) {
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

    JPAQuery<M2Character> jpaQuery = this.queryFactory
        .selectFrom(m2Character)
        .where(booleanBuilder);

    if (pageable != null) {
      jpaQuery
          .limit(pageable.getPageSize())
          .offset(pageable.getOffset());
      List<Sort.Order> orderList = pageable.getSort().stream().collect(Collectors.toList());
      if (!orderList.isEmpty() && orderList.get(0).getProperty().equalsIgnoreCase("DESC")) {
        jpaQuery.orderBy(m2Character.characterNo.desc());
      } else {
        jpaQuery.orderBy(m2Character.characterNo.asc());
      }
    }

    QueryResults<M2Character> queryResults = jpaQuery.fetchResults();

    return new PageImpl<>(
        queryResults.getResults(),
        pageable,
        queryResults.getTotal());
  }
}
