package me.math3ussdl.infrastructure.mapper;

import me.math3ussdl.domain.data.MatchDto;
import me.math3ussdl.infrastucture.entity.Match;
import me.math3ussdl.infrastucture.mapper.MatchMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatchMapperImplTest {

    @Spy
    private MatchMapper mapper = Mappers.getMapper(MatchMapper.class);

    @Test
    @DisplayName("It should mapping Match to MatchDto returning a valid instance")
    public void testGetMatchDto_ReturnValidInstance() {
        // Arrange
        UUID random = UUID.randomUUID();
        OffsetDateTime now = OffsetDateTime.now();
        String findWords = "ladal,radar";

        Match match = new Match(random, now, findWords);
        MatchDto expectedDto = new MatchDto(random, now, findWords);

        when(mapper.matchToMatchDto(match)).thenReturn(expectedDto);

        // Act
        MatchDto actualDto = mapper.matchToMatchDto(match);

        // Assert
        assertThat(expectedDto).isEqualTo(actualDto);
        assertThat(actualDto.hashCode()).isEqualTo(expectedDto.hashCode());
    }

    @Test
    @DisplayName("It should mapping Match to MatchDto returning null value")
    public void testGetMatchDto_ReturnNull() {
        // Arrange
        when(mapper.matchToMatchDto(null)).thenReturn(null);

        // Act
        MatchDto actualDto = mapper.matchToMatchDto(null);

        // Assert
        assertThat(actualDto).isNull();
    }

    @Test
    @DisplayName("It should mapping Match List to MatchDto List returning null value")
    public void testGetMatchDtoList_ReturnNull() {
        // Arrange
        when(mapper.matchListToMatchDtoList(null)).thenReturn(null);

        // Act
        List<MatchDto> actualDtos = mapper.matchListToMatchDtoList(null);

        // Assert
        assertThat(actualDtos).isNull();
    }
}
