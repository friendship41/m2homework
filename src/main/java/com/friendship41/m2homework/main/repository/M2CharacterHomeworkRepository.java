package com.friendship41.m2homework.main.repository;

import com.friendship41.m2homework.main.data.entity.Homework;
import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.entity.M2CharacterHomework;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface M2CharacterHomeworkRepository extends JpaRepository<M2CharacterHomework, Integer> {
  Page<M2CharacterHomework> findAllByM2Character(M2Character m2Character, Pageable pageable);

  List<M2CharacterHomework> findAllByM2CharacterAndHomework(M2Character m2Character, Homework homework);
}
