package me.math3ussdl.service;

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
     * Verifica se uma subsequência de caracteres em um array é um palíndromo.
     *
     * @param arr   O array de caracteres contendo a subsequência a ser verificada.
     * @param start O índice inicial da subsequência no array.
     * @param end   O índice final da subsequência no array.
     * @return True se a subsequência for um palíndromo, caso contrário, False.
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
     * Obtém uma coluna específica de uma matriz de caracteres.
     *
     * @param matrix    A matriz de caracteres da qual obter a coluna.
     * @param colIndex  O índice da coluna desejada na matriz.
     * @return Um array de caracteres representando a coluna especificada da matriz.
     */
    private char[] getColumn(char[][] matrix, int colIndex) {
        char[] column = new char[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            column[row] = matrix[row][colIndex];
        }
        return column;
    }

    /**
     * Obtém uma diagonal específica de uma matriz de caracteres, a partir de uma posição inicial.
     *
     * @param matrix    A matriz de caracteres da qual obter a diagonal.
     * @param row       O índice da linha inicial da diagonal na matriz.
     * @param col       O índice da coluna inicial da diagonal na matriz.
     * @return Um array de caracteres representando a diagonal especificada da matriz.
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
