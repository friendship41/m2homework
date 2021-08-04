package com.friendship41.m2homework.main.data.response;

import com.friendship41.m2homework.main.data.type.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResCharacter {
  private int characterNo;
  private int memberNo;
  private String characterName;
  private JobType jobType;
  private Boolean isMain;
  private Integer characterImageNo;
}
