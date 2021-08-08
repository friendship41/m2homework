package com.friendship41.m2homework.main.data.response;

import com.friendship41.m2homework.main.data.type.HomeworkTargetType;
import com.friendship41.m2homework.main.data.type.HomeworkType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResHomework {
  private int homeworkNo;
  private String homeworkName;
  private HomeworkType homeworkType;
  private HomeworkTargetType homeworkTargetType;
  private Integer unitGoal;
  private String unitResetPeriod;
  private Integer maxGoal;
  private String allResetPeriod;
}
