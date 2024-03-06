package me.math3ussdl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Entity(name = "matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private OffsetDateTime matchDate;

    private ArrayList<String> findWords;

    @PrePersist
    void prePersist() {
        if (this.matchDate == null) {
            this.matchDate = OffsetDateTime.now();
        }
    }
}
