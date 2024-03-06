package me.math3ussdl.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private OffsetDateTime matchDate;

    private String findWords;

    public Match(UUID id, OffsetDateTime matchDate, String findWords) {
        this.id = id;
        this.matchDate = matchDate;
        this.findWords = findWords;
    }

    public Match() {}

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
        if (!(o instanceof Match match)) return false;
        return Objects.equals(getId(), match.getId())
                && Objects.equals(getMatchDate(), match.getMatchDate())
                && Objects.equals(getFindWords(), match.getFindWords());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMatchDate(), getFindWords());
    }

    @PrePersist
    void prePersist() {
        if (this.matchDate == null) {
            this.matchDate = OffsetDateTime.now();
        }
    }
}
