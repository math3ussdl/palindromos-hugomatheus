package me.math3ussdl.port.api;

import me.math3ussdl.exception.MatrixMalformedException;

import java.util.List;

public interface MatchServicePort {

    /**
     * Encontra todos os palíndromos em uma matriz de caracteres, considerando as posições horizontal, vertical e diagonal.
     *
     * @param matrix A matriz de caracteres a ser analisada em busca de palíndromos.
     * @return Uma lista contendo todos os palíndromos encontrados na matriz.
     * @throws MatrixMalformedException Se a matriz não estiver no formato adequado.
     */
    List<String> findPalindromes(char[][] matrix) throws MatrixMalformedException;
}
