package me.math3ussdl.domain.data;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public class MatchDto {

    private UUID id;
    private OffsetDateTime matchDate;
    private String findWords;

    public MatchDto(UUID id, OffsetDateTime matchDate, String findWords) {
        this.id = id;
        this.matchDate = matchDate;
        this.findWords = findWords;
    }

    public MatchDto() { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OffsetDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(OffsetDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public String getFindWords() {
        return findWords;
    }

    public void setFindWords(String findWords) {
        this.findWords = findWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchDto matchDto)) return false;
        return Objects.equals(getId(), matchDto.getId())
                && Objects.equals(getMatchDate(), matchDto.getMatchDate())
                && Objects.equals(getFindWords(), matchDto.getFindWords());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMatchDate(), getFindWords());
    }
}
