package com.friendship41.m2homework.main.repository;

import com.friendship41.m2homework.main.data.entity.M2Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface M2CharacterRepository extends JpaRepository<M2Character, Integer> {
}
