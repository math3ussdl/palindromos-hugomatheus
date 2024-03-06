package me.math3ussdl.repository;

import me.math3ussdl.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {

    @Query("SELECT m FROM matches m :word MEMBER OF m.findWords")
    List<Match> findAllByWord(String word);
}
