package com.friendship41.m2homework.main.mapper;

import com.friendship41.m2homework.common.mapper.GenericMapper;
import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.response.ResponseCharacter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface M2CharacterMapper extends GenericMapper<ResponseCharacter, M2Character> {
}
