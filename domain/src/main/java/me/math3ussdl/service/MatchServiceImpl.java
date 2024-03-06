package me.math3ussdl.service;

import me.math3ussdl.data.MatchDto;
import me.math3ussdl.exception.MatrixMalformedException;
import me.math3ussdl.port.api.MatchServicePort;
import me.math3ussdl.port.spi.MatchPersistencePort;

import java.util.ArrayList;
import java.util.List;

public class MatchServiceImpl implements MatchServicePort {

    private final MatchPersistencePort persistence;

    public MatchServiceImpl(MatchPersistencePort persistence) {
        this.persistence = persistence;
    }

    @Override
    public List<String> findPalindromes(char[][] matrix) throws MatrixMalformedException {
        validateMatrixSize(matrix);

        List<String> palindromes = new ArrayList<>();

        findRowPalindromes(matrix, palindromes);
        findColumnPalindromes(matrix, palindromes);
        findDiagonalPalindromes(matrix, palindromes);

        persistence.saveMatch(palindromes);
        return palindromes;
    }

    @Override
    public List<MatchDto> findAllMatches() {
        return persistence.getMatches();
    }

    @Override
    public List<MatchDto> findMatchesByWordIncidence(String word) {
        return persistence.getMatches(word);
    }

    // private methods...

    /**
     * This method validates the size of a matrix.
     *
     * @param matrix The matrix to be validated.
     * @throws MatrixMalformedException Thrown when the matrix is empty or has incorrect dimensions.
     */
    private void validateMatrixSize(char[][] matrix) throws MatrixMalformedException {
        int rows = matrix.length;

        if (rows == 0) {
            throw new MatrixMalformedException(
                "Empty matrix! Verify the matrix and try again.");
        }

        int columns = matrix[0].length;

        if (columns > 10 || rows != columns) {
            throw new MatrixMalformedException(
                    "Incorrect matrix! Verify the matrix and try again.");
        }
    }

    /**
     * This method finds palindromes in each row of the matrix.
     *
     * @param matrix The matrix to search for palindromes.
     * @param palindromes A list to store the found palindromes.
     */
    private void findRowPalindromes(char[][] matrix, List<String> palindromes) {
        for (char[] row : matrix) {
            for (int start = 0; start <= row.length - 3; start++) {
                for (int end = start + 2; end < row.length; end++) {
                    if (isPalindrome(row, start, end)) {
                        palindromes.add(new String(row, start, end - start + 1));
                    }
                }
            }
        }
    }

    /**
     * This method finds palindromes in each column of the matrix.
     *
     * @param matrix The matrix to search for palindromes.
     * @param palindromes A list to store the found palindromes.
     */
    private void findColumnPalindromes(char[][] matrix, List<String> palindromes) {
        for (int column = 0; column < matrix[0].length; column++) {
            char[] columnChars = getColumn(matrix, column);
            for (int start = 0; start <= columnChars.length - 3; start++) {
                for (int end = start + 2; end < columnChars.length; end++) {
                    if (isPalindrome(columnChars, start, end)) {
                        palindromes.add(new String(columnChars, start, end - start + 1));
                    }
                }
            }
        }
    }

    /**
     * This method finds palindromes in each diagonal of the matrix.
     *
     * @param matrix The matrix to search for palindromes.
     * @param palindromes A list to store the found palindromes.
     */
    private void findDiagonalPalindromes(char[][] matrix, List<String> palindromes) {
        for (int row = 0; row <= matrix.length - 3; row++) {
            for (int column = 0; column <= matrix[row].length - 3; column++) {
                char[] diagonalChars = getDiagonal(matrix, row, column);
                for (int start = 0, end = diagonalChars.length - 1; start <= end - 2; start++, end--) {
                    if (isPalindrome(diagonalChars, start, end)) {
                        palindromes.add(new String(diagonalChars, start, end - start + 1));
                    }
                }
            }
        }
    }

    /**
     * This method checks if a substring of a character array is a palindrome.
     *
     * @param arr The character array.
     * @param start The starting index of the substring.
     * @param end The ending index of the substring.
     * @return True if the substring is a palindrome, otherwise false.
     */
    private boolean isPalindrome(char[] arr, int start, int end) {
        while (start < end) {
            if (arr[start++] != arr[end--]) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method retrieves a specific column from a matrix.
     *
     * @param matrix The matrix from which to retrieve the column.
     * @param colIndex The index of the column to retrieve.
     * @return The column as a character array.
     */
    private char[] getColumn(char[][] matrix, int colIndex) {
        char[] column = new char[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            column[row] = matrix[row][colIndex];
        }
        return column;
    }

    /**
     * This method retrieves characters from a diagonal of a matrix starting from a specific row and column.
     *
     * @param matrix The matrix from which to retrieve the diagonal.
     * @param row The starting row index of the diagonal.
     * @param col The starting column index of the diagonal.
     * @return The diagonal elements as a character array.
     */
    private char[] getDiagonal(char[][] matrix, int row, int col) {
        List<Character> diagonal = new ArrayList<>();
        while (row < matrix.length && col < matrix[row].length) {
            diagonal.add(matrix[row++][col++]);
        }
        char[] result = new char[diagonal.size()];
        for (int i = 0; i < diagonal.size(); i++) {
            result[i] = diagonal.get(i);
        }
        return result;
    }
}
