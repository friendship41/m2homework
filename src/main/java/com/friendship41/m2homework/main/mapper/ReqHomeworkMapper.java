package com.friendship41.m2homework.main.mapper;

import com.friendship41.m2homework.common.mapper.GenericMapper;
import com.friendship41.m2homework.main.data.entity.Homework;
import com.friendship41.m2homework.main.data.request.ReqHomework;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReqHomeworkMapper extends GenericMapper<ReqHomework, Homework> {
}
