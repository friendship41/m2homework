package com.friendship41.m2homework.main.mapper;

import com.friendship41.m2homework.common.mapper.GenericMapper;
import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.request.ReqCharacter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReqCharacterMapper extends GenericMapper<M2Character, ReqCharacter> {
}
