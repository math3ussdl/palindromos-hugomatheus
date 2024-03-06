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
        List<String> palindromes = new ArrayList<>();

        if (matrix.length > 10 || matrix[0].length > 10) {
            throw new MatrixMalformedException(
                    "Incorrect matrix! The size of rows and columns exceeds the value of 10");
        }

        for (char[] chars : matrix) {
            for (int j = 0; j <= chars.length - 3; j++) {
                for (int k = j + 2; k < chars.length; k++) {
                    if (isPalindrome(chars, j, k)) {
                        palindromes.add(new String(chars, j, k - j + 1));
                    }
                }
            }
        }

        for (int i = 0; i <= matrix.length - 3; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                for (int k = i + 2; k < matrix.length; k++) {
                    if (isPalindrome(getColumn(matrix, j), i, k)) {
                        palindromes.add(new String(getColumn(matrix, j), i, k - i + 1));
                    }
                }
            }
        }

        for (int i = 0; i <= matrix.length - 3; i++) {
            for (int j = 0; j <= matrix[i].length - 3; j++) {
                for (int k = i + 2, l = j + 2; k < matrix.length && l < matrix[k].length; k++, l++) {
                    if (isPalindrome(getDiagonal(matrix, i, j), 0, k - i)) {
                        palindromes.add(new String(getDiagonal(matrix, i, j), 0, k - i + 1));
                    }
                }
            }
        }

        persistence.saveMatch(palindromes);
        return palindromes;
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
        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][colIndex];
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
