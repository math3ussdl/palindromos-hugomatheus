package me.math3ussdl.adapter;

import me.math3ussdl.data.MatchDto;
import me.math3ussdl.entity.Match;
import me.math3ussdl.mapper.MatchMapper;
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

    @Override
    public List<MatchDto> getMatches() {
        List<Match> matches = repository.findAll();
        return MatchMapper.INSTANCE.matchListToMatchDtoList(matches);
    }

    @Override
    public List<MatchDto> getMatches(String searchedWord) {
        List<Match> matches = repository.findAllByWord(searchedWord);
        return MatchMapper.INSTANCE.matchListToMatchDtoList(matches);
    }
}
