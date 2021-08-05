package com.friendship41.m2homework.common.data.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ImageData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int imageNo;
  private String imageLocation;
  private String originFileName;
  private String storedFileName;
  private String ext;
  private Long size;
  private Date createDate;
  private String accessScopes;

  @PrePersist
  public void prePersist() {
    this.createDate = this.createDate == null ? new Date() : this.createDate;
  }
}
