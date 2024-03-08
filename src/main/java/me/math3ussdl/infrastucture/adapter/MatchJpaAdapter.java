package me.math3ussdl.infrastucture.adapter;

import me.math3ussdl.domain.data.MatchDto;
import me.math3ussdl.domain.port.spi.MatchPersistencePort;
import me.math3ussdl.infrastucture.entity.Match;
import me.math3ussdl.infrastucture.mapper.MatchMapper;
import me.math3ussdl.infrastucture.repository.MatchRepository;
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

        if (!palindromes.isEmpty())
            match.setFindWords(String.join(",", palindromes));

        repository.save(match);
    }

    @Override
    public List<MatchDto> getMatches() {
        List<Match> matches = repository.findAll();
        return MatchMapper.INSTANCE.matchListToMatchDtoList(matches);
    }

    @Override
    public List<MatchDto> getMatches(ArrayList<String> searchedWords) {
        List<Match> matches = repository.findByFindWordsContaining(String.join(",", searchedWords));
        return MatchMapper.INSTANCE.matchListToMatchDtoList(matches);
    }
}
