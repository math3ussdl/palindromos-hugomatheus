package me.math3ussdl.port.spi;

import me.math3ussdl.data.MatchDto;

import java.util.List;
import java.util.Optional;

public interface MatchPersistencePort {

    /**
     * Saves palindrome matches.
     *
     * @param palindromes List of palindromes to be saved.
     */
    void saveMatch(List<String> palindromes);

    /**
     * Searches all palindrome matches, containing the list of words found in each one.
     *
     * @return List of palindromes' matches
     */
    List<MatchDto> getMatches();

    /**
     * Searches all palindrome matches, containing the list of words found in each one.
     *
     * @param searchedWord A keyword that will bring up the lists containing it
     * @return List of palindromes' matches
     */
    List<MatchDto> getMatches(String searchedWord);
}
