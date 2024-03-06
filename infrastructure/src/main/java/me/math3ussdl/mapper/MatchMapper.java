package me.math3ussdl.mapper;

import me.math3ussdl.data.MatchDto;
import me.math3ussdl.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    List<MatchDto> matchListToMatchDtoList(List<Match> matchList);
}
