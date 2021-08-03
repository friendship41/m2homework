package com.friendship41.m2homework.main.data.request;

import com.friendship41.m2homework.main.data.type.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqCharacter {
  private String characterName;
  private JobType jobType;
  private Boolean isMain;
}
