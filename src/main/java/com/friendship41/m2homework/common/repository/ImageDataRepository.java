package com.friendship41.m2homework.common.repository;

import com.friendship41.m2homework.common.data.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Integer> {
}
