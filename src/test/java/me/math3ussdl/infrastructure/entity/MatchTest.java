package me.math3ussdl.infrastructure.entity;

import me.math3ussdl.infrastucture.entity.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {

    private Match match;

    @BeforeEach
    void setUp() {
        UUID id = UUID.randomUUID();
        OffsetDateTime matchDate = OffsetDateTime.now();
        String findWords = "example";
        match = new Match(id, matchDate, findWords);
    }

    @Test
    @DisplayName("It should set the match id successfully")
    void testGetId() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        match.setId(id);

        // Assert
        assertThat(id).isEqualTo(match.getId());
    }

    @Test
    @DisplayName("It should set the match date successfully")
    void testGetMatchDate() {
        // Arrange
        OffsetDateTime matchDate = OffsetDateTime.now();

        // Act
        match.setMatchDate(matchDate);

        // Assert
        assertThat(matchDate).isEqualTo(match.getMatchDate());
    }

    @Test
    @DisplayName("It should set the match find words successfully")
    void testGetFindWords() {
        // Arrange
        String findWords = "example";

        // Act
        match.setFindWords(findWords);

        // Assert
        assertThat(findWords).isEqualTo(match.getFindWords());
    }

    @Test
    @DisplayName("It should return true for equals function, passing two matches")
    void testEquals() {
        // Arrange
        UUID id = UUID.randomUUID();
        OffsetDateTime matchDate = OffsetDateTime.now();
        String findWords = "example";

        Match match1 = new Match(id, matchDate, findWords);
        Match match2 = new Match(id, matchDate, findWords);

        // Act
        boolean isEql = match1.equals(match2);

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

        Match match1 = new Match(id1, matchDate, findWords);
        Match match2 = new Match(id2, matchDate, findWords);

        // Act
        boolean isEql = match1.equals(match2);

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

        Match match1 = new Match(id, matchDate1, findWords);
        Match match2 = new Match(id, matchDate2, findWords);

        // Act
        boolean isEql = match1.equals(match2);

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

        Match match1 = new Match(id, matchDate, findWords1);
        Match match2 = new Match(id, matchDate, findWords2);

        // Act
        boolean isEql = match1.equals(match2);

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

        Match match1 = new Match(id, matchDate, findWords1);

        // Act
        boolean isEql = match1.equals(new Object());

        // Assert
        assertThat(isEql).isFalse();
    }

    @Test
    @DisplayName("It should return true for hashCode function, passing two matches (same hash)")
    void testHashCode() {
        // Arrange
        UUID id = UUID.randomUUID();
        OffsetDateTime matchDate = OffsetDateTime.now();
        String findWords = "example";

        Match match1 = new Match(id, matchDate, findWords);
        Match match2 = new Match(id, matchDate, findWords);

        // Act
        int hashCodeMatch1 = match1.hashCode();
        int hashCodeMatch2 = match2.hashCode();

        // Assert
        assertThat(hashCodeMatch1).isEqualTo(hashCodeMatch2);
    }

    @Test
    @DisplayName("It should persist a new match adding a new date")
    void testPrePersist() {
        // Arrange
        Match m = new Match();

        // Act
        m.prePersist();

        // Assert
        assertThat(m.getMatchDate()).isNotNull();
    }
}
