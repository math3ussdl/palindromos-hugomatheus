package me.math3ussdl.service;

import me.math3ussdl.exception.MatrixMalformedException;
import me.math3ussdl.port.api.MatchServicePort;
import me.math3ussdl.port.spi.MatchPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MatchServiceImplTest {

    @Mock
    private MatchPersistencePort persistence;

    private MatchServicePort matchService;

    @BeforeEach
    void setUp() {
        matchService = new MatchServiceImpl(persistence);
    }

    @Test
    @DisplayName("It should return a list of palindromes, passing valid matrix")
    public void testFindPalindromes_WithValidMatrix_ShouldReturnListOfPalindromes()
            throws MatrixMalformedException {

        // Arrange
        char[][] matrix = {
            {'a', 'b', 'c', 'd'},
            {'e', 'f', 'g', 'h'},
            {'i', 'j', 'f', 'l'},
            {'m', 'n', 'o', 'a'}
        };

        // Act
        List<String> palindromes = matchService.findPalindromes(matrix);

        // Assert
        assertThat(palindromes.size()).isEqualTo(1);
        assertThat(palindromes.get(0)).isEqualTo("affa");
        verify(persistence, times(1)).saveMatch(palindromes);
    }

    @Test
    @DisplayName("It should throws a MatrixMalformedException, passing invalid matrix")
    public void testFindPalindromes_WithInvalidMatrixSize_ShouldThrowMatrixMalformedException() {

        // Arrange
        char[][] matrix = {
            {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'},
            {'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'},
            {'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd'},
            {'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'}
        };

        // Act
        assertThat(catchThrowable(() -> matchService.findPalindromes(matrix)))
                .as("Incorrect matrix! Verify the matrix and try again.")
                .isInstanceOf(MatrixMalformedException.class)
                .hasMessageContaining("Incorrect matrix!");
    }

    @Test
    @DisplayName("It should throws a MatrixMalformedException, passing invalid matrix column size")
    public void testFindPalindromes_WithInvalidColumnSize_ShouldThrowMatrixMalformedException() {

        // Arrange
        char[][] matrix = {
            {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'},
            {'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'},
            {'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 's'},
            {'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'}
        };

        // Act
        assertThat(catchThrowable(() -> matchService.findPalindromes(matrix)))
                .as("Incorrect matrix! Verify the matrix and try again.")
                .isInstanceOf(MatrixMalformedException.class)
                .hasMessageContaining("Incorrect matrix!");
    }

    @Test
    @DisplayName("It should throws a MatrixMalformedException, passing empty matrix")
    public void testFindPalindromes_WithEmptyMatrix_ShouldReturnEmptyList() {
        // Arrange
        char[][] matrix = {};

        // Act
        assertThat(catchThrowable(() -> matchService.findPalindromes(matrix)))
                .as("Empty matrix! Verify the matrix and try again.")
                .isInstanceOf(MatrixMalformedException.class)
                .hasMessageContaining("Empty matrix!");
    }

    @Test
    @DisplayName("It should return a empty list of palindromes, passing a single character matrix")
    public void testFindPalindromes_WithSingleCharacterMatrix_ShouldReturnEmptyList() throws MatrixMalformedException {
        // Arrange
        char[][] matrix = {{'a'}};

        // Act
        List<String> palindromes = matchService.findPalindromes(matrix);

        // Assert
        assertThat(palindromes.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("It should return a empty list of palindromes, passing a matrix without palindromes")
    public void testFindPalindromes_WithNoPalindromesInMatrix_ShouldReturnEmptyList() throws MatrixMalformedException {
        // Arrange
        char[][] matrix = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'}
        };

        // Act
        List<String> palindromes = matchService.findPalindromes(matrix);

        // Assert
        assertThat(palindromes.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("It should find palindromes and save it using the persistence port")
    public void testFindPalindromes_WithMockPersistence_ShouldSaveMatches() throws MatrixMalformedException {
        // Arrange
        char[][] matrix = {
            {'a', 'b', 'c', 'd'},
            {'e', 'f', 'g', 'h'},
            {'i', 'j', 'k', 'l'},
            {'m', 'n', 'o', 'p'}
        };

        // Act
        matchService.findPalindromes(matrix);

        // Assert
        verify(persistence, times(1)).saveMatch(anyList());
    }
}
