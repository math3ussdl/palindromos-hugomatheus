package me.math3ussdl.service;

import me.math3ussdl.data.MatchDto;
import me.math3ussdl.exception.MatrixMalformedException;
import me.math3ussdl.port.api.MatchServicePort;
import me.math3ussdl.port.spi.MatchPersistencePort;

import java.util.*;

public class MatchServiceImpl implements MatchServicePort {

    private static final int MAX_SIZE = 10;

    private final MatchPersistencePort persistence;
    public MatchServiceImpl(MatchPersistencePort persistence) {
        this.persistence = persistence;
    }

    @Override
    public List<MatchDto> findAllMatches() {
        return persistence.getMatches();
    }

    @Override
    public List<MatchDto> findMatchesByWordIncidence(ArrayList<String> searchedWords) {
        return persistence.getMatches(searchedWords);
    }

    @Override
    public List<String> findPalindromes(char[][] matrix) throws MatrixMalformedException {
        validateMatrixSize(matrix);
        List<String> palindromes = findAllPalindromes(matrix);
        persistence.saveMatch(palindromes);
        return palindromes;
    }

    /**
     * This method validates the size of a matrix.
     *
     * @param matrix The matrix to be validated.
     * @throws MatrixMalformedException Thrown when the matrix is empty or has incorrect dimensions.
     */
    private void validateMatrixSize(char[][] matrix) throws MatrixMalformedException {
        String msg = null;

        if (matrix.length == 0 || matrix.length > MAX_SIZE || matrix[0].length > MAX_SIZE) {
            msg = "Invalid matrix size! Verify the matrix and try again.";
        }

        for (char[] row : matrix) {
            if (row.length != matrix.length) {
                msg = "This isn't a 2d matrix! Verify the matrix and try again.";
                break;
            }
        }

        if (msg != null)
            throw new MatrixMalformedException(msg);
    }

    private List<String> findAllPalindromes(char[][] matrix) {
        List<String> palindromes = new ArrayList<>();

        int rows = matrix.length;
        int cols = matrix[0].length;

        Map<Character, List<int[]>> charPositions = new HashMap<>();

        // Check horizontally
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                checkPalindromes(matrix, i, j, 0, 1, palindromes, charPositions);
            }
        }

        // Check vertically
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j < cols; j++) {
                checkPalindromes(matrix, i, j, 1, 0, palindromes, charPositions);
            }
        }

        // Check diagonally (top-left to bottom-right)
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                checkPalindromes(matrix, i, j, 1, 1, palindromes, charPositions);
            }
        }

        // Check diagonally (top-right to bottom-left)
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = cols - 1; j >= 2; j--) {
                checkPalindromes(matrix, i, j, 1, -1, palindromes, charPositions);
            }
        }

        return palindromes;
    }

    private void checkPalindromes(char[][] matrix, int row, int col, int dx, int dy,
                                  List<String> palindromes, Map<Character, List<int[]>> charPositions) {

        StringBuilder sb = new StringBuilder();
        String longestPalindrome = "";

        int currentRow = row;
        int currentCol = col;

        while (currentRow >= 0
                && currentRow < matrix.length
                && currentCol >= 0
                && currentCol < matrix[0].length) {

            sb.append(matrix[currentRow][currentCol]);

            if (sb.length() >= 4) {
                String current = sb.toString();
                if (isPalindrome(current)
                        && current.length() > longestPalindrome.length()
                        && !isDuplicatePosition(charPositions, currentRow, currentCol)
                ) {
                    longestPalindrome = current;
                    populateCharPositions(charPositions, current, row, col, dx, dy);
                }
            }

            currentRow += dx;
            currentCol += dy;
        }

        if (!longestPalindrome.isEmpty()) {
            palindromes.add(longestPalindrome);
        }
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    private boolean isDuplicatePosition(Map<Character, List<int[]>> charPositions, int row, int col) {
        for (List<int[]> positions : charPositions.values()) {
            for (int[] position : positions) {
                if (position[0] == row && position[1] == col) {
                    return true;
                }
            }
        }
        return false;
    }

    private void populateCharPositions(Map<Character, List<int[]>> charPositions,
                                       String palindrome, int row, int col, int dx, int dy) {
        char[] chars = palindrome.toCharArray();
        int r = row;
        int c = col;

        for (char ch : chars) {
            charPositions.putIfAbsent(ch, new ArrayList<>());
            charPositions.get(ch).add(new int[]{r, c});
            r += dx;
            c += dy;
        }
    }
}
