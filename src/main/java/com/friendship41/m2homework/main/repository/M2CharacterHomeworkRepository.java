package com.friendship41.m2homework.main.repository;

import com.friendship41.m2homework.main.data.entity.MemberHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface M2CharacterHomeworkRepository extends JpaRepository<MemberHomework, Integer> {
}
