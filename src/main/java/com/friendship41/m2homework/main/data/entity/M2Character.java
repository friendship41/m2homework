package com.friendship41.m2homework.main.data.entity;

import com.friendship41.m2homework.main.data.type.JobType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class M2Character {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int characterNo;
  private int memberNo;
  private String characterName;
  private JobType jobType;
  private Boolean isMain;
  private Integer characterImageNo;
}
