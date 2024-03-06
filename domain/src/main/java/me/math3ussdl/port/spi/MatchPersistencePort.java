package me.math3ussdl.port.spi;

import java.util.List;

public interface MatchPersistencePort {

    /**
     * Salva as correspondências de palíndromos.
     *
     * @param palindromes Lista de palíndromos a serem salvos.
     */
    void saveMatch(List<String> palindromes);
}
