package me.math3ussdl.infrastucture.repository;

import me.math3ussdl.infrastucture.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {
    List<Match> findByFindWordsContaining(String searchTerm);
}
