package me.math3ussdl.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchDtoTest {

    private MatchDto matchDto;

    @BeforeEach
    public void setUp() {
        UUID id = UUID.randomUUID();
        OffsetDateTime matchDate = OffsetDateTime.now();
        String findWords = "example";
        matchDto = new MatchDto(id, matchDate, findWords);
    }

    @Test
    @DisplayName("It should set the match id successfully")
    public void testGetId() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        matchDto.setId(id);

        // Assert
        assertThat(id).isEqualTo(matchDto.getId());
    }

    @Test
    @DisplayName("It should set the match date successfully")
    public void testGetMatchDate() {
        // Arrange
        OffsetDateTime matchDate = OffsetDateTime.now();

        // Act
        matchDto.setMatchDate(matchDate);

        // Assert
        assertThat(matchDate).isEqualTo(matchDto.getMatchDate());
    }

    @Test
    @DisplayName("It should set the match find words successfully")
    public void testGetFindWords() {
        // Arrange
        String findWords = "example";

        // Act
        matchDto.setFindWords(findWords);

        // Assert
        assertThat(findWords).isEqualTo(matchDto.getFindWords());
    }

    @Test
    @DisplayName("It should return true for equals function, passing two matches")
    public void testEquals() {
        // Arrange
        UUID id = UUID.randomUUID();
        OffsetDateTime matchDate = OffsetDateTime.now();
        String findWords = "example";

        MatchDto matchDto1 = new MatchDto(id, matchDate, findWords);
        MatchDto matchDto2 = new MatchDto(id, matchDate, findWords);

        // Act
        boolean isEql = matchDto1.equals(matchDto2);

        // Assert
        assertThat(isEql).isTrue();
    }

    @Test
    @DisplayName("It should return false for equals function, passing two matches (different id)")
    public void testNotEquals_MatchId() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        OffsetDateTime matchDate = OffsetDateTime.now();
        String findWords = "example";

        MatchDto matchDto1 = new MatchDto(id1, matchDate, findWords);
        MatchDto matchDto2 = new MatchDto(id2, matchDate, findWords);

        // Act
        boolean isEql = matchDto1.equals(matchDto2);

        // Assert
        assertThat(isEql).isFalse();
    }

    @Test
    @DisplayName("It should return false for equals function, passing two matches (different date)")
    public void testNotEquals_MatchDate() {
        // Arrange
        UUID id = UUID.randomUUID();
        OffsetDateTime matchDate1 = OffsetDateTime.now();
        OffsetDateTime matchDate2 = OffsetDateTime.now().minusHours(2);
        String findWords = "example";

        MatchDto matchDto1 = new MatchDto(id, matchDate1, findWords);
        MatchDto matchDto2 = new MatchDto(id, matchDate2, findWords);

        // Act
        boolean isEql = matchDto1.equals(matchDto2);

        // Assert
        assertThat(isEql).isFalse();
    }

    @Test
    @DisplayName("It should return false for equals function, passing two matches (different find words)")
    public void testNotEquals_MatchFindWords() {
        // Arrange
        UUID id = UUID.randomUUID();
        OffsetDateTime matchDate = OffsetDateTime.now();
        String findWords1 = "example";
        String findWords2 = "example2";

        MatchDto matchDto1 = new MatchDto(id, matchDate, findWords1);
        MatchDto matchDto2 = new MatchDto(id, matchDate, findWords2);

        // Act
        boolean isEql = matchDto1.equals(matchDto2);

        // Assert
        assertThat(isEql).isFalse();
    }

    @Test
    @DisplayName("It should return false for equals function, passing two object (different instances)")
    public void testNotEquals_MatchAndOtherObj() {
        // Arrange
        UUID id = UUID.randomUUID();
        OffsetDateTime matchDate = OffsetDateTime.now();
        String findWords1 = "example";

        MatchDto matchDto1 = new MatchDto(id, matchDate, findWords1);

        // Act
        boolean isEql = matchDto1.equals(new Object());

        // Assert
        assertThat(isEql).isFalse();
    }

    @Test
    @DisplayName("It should return true for hashCode function, passing two matches (same hash)")
    public void testHashCode() {
        // Arrange
        UUID id = UUID.randomUUID();
        OffsetDateTime matchDate = OffsetDateTime.now();
        String findWords = "example";

        MatchDto matchDto1 = new MatchDto(id, matchDate, findWords);
        MatchDto matchDto2 = new MatchDto(id, matchDate, findWords);

        // Act
        int hashCodeMatch1 = matchDto1.hashCode();
        int hashCodeMatch2 = matchDto2.hashCode();

        // Assert
        assertThat(hashCodeMatch1).isEqualTo(hashCodeMatch2);
    }
}
