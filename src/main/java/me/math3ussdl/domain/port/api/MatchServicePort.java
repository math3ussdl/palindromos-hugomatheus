package me.math3ussdl.domain.port.api;

import me.math3ussdl.domain.data.MatchDto;
import me.math3ussdl.domain.exception.MatrixMalformedException;

import java.util.ArrayList;
import java.util.List;

public interface MatchServicePort {

    /**
     * Finds all the palindromes in a character matrix, considering the horizontal, vertical and diagonal positions.
     *
     * @param matrix The character matrix to be analyzed for palindromes.
     * @return A list containing all the palindromes found in the matrix and saves these words in the database.
     * @throws MatrixMalformedException If the matrix is not in the right format.
     */
    List<String> findPalindromes(char[][] matrix) throws MatrixMalformedException;

    /**
     * Finds all the matches containing the words found.
     *
     * @return A list of all the matches stored in the database.
     */
    List<MatchDto> findAllMatches();

    /**
     * Find all the matches containing the words found and containing a specific word.
     *
     * @param searchedWords A list of keywords that will be contained in all palindrome matches
     * @return A list of all the matches stored in the database.
     */
    List<MatchDto> findMatchesByWordIncidence(ArrayList<String> searchedWords);
}
