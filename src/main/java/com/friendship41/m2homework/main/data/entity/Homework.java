package com.friendship41.m2homework.main.data.entity;

import com.friendship41.m2homework.main.data.type.HomeworkTargetType;
import com.friendship41.m2homework.main.data.type.HomeworkType;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Homework {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int homeworkNo;
  private String homeworkName;
  private HomeworkType homeworkType;
  private HomeworkTargetType homeworkTargetType;
  private Integer unitGoal;
  private String unitResetPeriod;
  private Integer maxGoal;
  private String allResetPeriod;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "homeworkNo")
  private List<MemberHomework> memberHomeworkList;
}
