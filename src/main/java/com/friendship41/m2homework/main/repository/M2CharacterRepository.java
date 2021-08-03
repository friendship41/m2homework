package com.friendship41.m2homework.main.repository;

import com.friendship41.m2homework.main.data.entity.M2Character;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface M2CharacterRepository extends JpaRepository<M2Character, Integer> {
  List<M2Character> findM2CharacterByMemberNoAndIsMain(Integer memberNo, Boolean isMain);
}
