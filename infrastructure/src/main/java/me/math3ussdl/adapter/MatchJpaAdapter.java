package me.math3ussdl.adapter;

import me.math3ussdl.entity.Match;
import me.math3ussdl.port.spi.MatchPersistencePort;
import me.math3ussdl.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchJpaAdapter implements MatchPersistencePort {

    @Autowired
    private MatchRepository repository;

    @Override
    public void saveMatch(List<String> palindromes) {
        Match match = new Match();
        match.setFindWords(new ArrayList<>(palindromes));
        repository.save(match);
    }
}
