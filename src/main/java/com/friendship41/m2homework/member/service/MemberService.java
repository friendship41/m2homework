package com.friendship41.m2homework.member.service;

import com.friendship41.m2homework.member.data.entity.Member;
import com.friendship41.m2homework.member.exception.MemberIdConstraintException;
import com.friendship41.m2homework.member.exception.MemberNotFoundException;
import com.friendship41.m2homework.member.repository.MemberRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class MemberService {
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public MemberService(final MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
    this.bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
  }

  public Member getMember(final Integer memberNo, final String memberId) throws MemberNotFoundException {
    Member member;
    if (memberNo == null && memberId == null) {
      throw new MemberNotFoundException("parameter (memberNo, memberId) is NULL");
    }
    if (memberNo != null) {
      member = this.memberRepository.findByMemberNo(memberNo)
          .orElseThrow(() -> new MemberNotFoundException(memberNo));
    } else {
      member = this.memberRepository.findByMemberId(memberId)
          .orElseThrow(() -> new MemberNotFoundException(memberNo, memberId));
    }
    return member;
  }

  public Member insertMember(Member member) throws MemberIdConstraintException {
    member.setPassword(this.bCryptPasswordEncoder.encode(member.getPassword()));
    try {
      return this.memberRepository.save(member);
    } catch (DataIntegrityViolationException e) {
      log.error("member insert fail, memberId: {}", member.getMemberId());
      throw new MemberIdConstraintException(e.getMessage(), e.getCause(), member.getMemberId());
    }
  }

  public Member memberlogin(int memberNo) {
    Member member = this.memberRepository.findById(memberNo)
        .orElseThrow(() -> new MemberNotFoundException(memberNo));
    member.setLoginDate(new Date());
    this.memberRepository.save(member);
    return member;
  }
}
