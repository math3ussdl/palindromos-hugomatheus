package me.math3ussdl.infrastucture.mapper;

import me.math3ussdl.domain.data.MatchDto;
import me.math3ussdl.infrastucture.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchDto matchToMatchDto(Match match);
    List<MatchDto> matchListToMatchDtoList(List<Match> matchList);
}
