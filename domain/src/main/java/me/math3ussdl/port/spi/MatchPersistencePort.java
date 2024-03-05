package me.math3ussdl.port.spi;

import me.math3ussdl.data.MatchDto;

import java.util.List;

public interface MatchPersistencePort {

    MatchDto saveMatch(List<String> palindromes);
}
