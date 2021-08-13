package com.friendship41.m2homework.main.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.friendship41.m2homework.member.data.entity.Member;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// 테스트중 Entity가 밖으로 나갈때를 위한 전략 (비추), DTO로 내보내길...
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "memberHomeworkNo")
@JsonIdentityReference(alwaysAsId = true)
public class MemberHomework {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int memberHomeworkNo;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memberNo")
  private Member member;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "homeworkNo")
  private Homework homework;
}
