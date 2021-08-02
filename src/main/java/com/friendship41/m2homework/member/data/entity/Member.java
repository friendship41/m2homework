package com.friendship41.m2homework.member.data.entity;

import com.friendship41.m2homework.auth.data.type.RoleType;
import com.friendship41.m2homework.main.data.entity.M2Character;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer memberNo;
  private String memberId;
  private String password;
  private Date regDate;
  private Date loginDate;
  private RoleType roleType;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "memberNo")
  private Collection<M2Character> m2Characters = new LinkedHashSet<>();

  @PrePersist
  public void prePersist() {
    this.regDate = this.regDate == null ? new Date() : this.regDate;
    this.loginDate = this.loginDate == null ? new Date() : this.loginDate;
    this.roleType = this.roleType == null ? RoleType.MEMBER : this.roleType;
  }
}
