package me.math3ussdl.domain.service;

import me.math3ussdl.domain.data.MatchDto;
import me.math3ussdl.domain.exception.MatrixMalformedException;
import me.math3ussdl.domain.port.api.MatchServicePort;
import me.math3ussdl.domain.port.spi.MatchPersistencePort;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

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
                {'a', 'j', 'j', 'a'},
                {'l', 'f', 'g', 'h'},
                {'l', 'g', 'f', 'l'},
                {'a', 'n', 'o', 'a'}
        };

        // Act
        List<String> palindromes = matchService.findPalindromes(matrix);

        // Assert
        assertThat(palindromes).hasSize(3);
        assertThat(palindromes.get(0)).isEqualTo("ajja");
        assertThat(palindromes.get(1)).isEqualTo("alla");
        assertThat(palindromes.get(2)).isEqualTo("affa");
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
                .as("This isn't a 2d matrix! Verify the matrix and try again.")
                .isInstanceOf(MatrixMalformedException.class)
                .hasMessageContaining("This isn't a 2d matrix!");
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
                .as("This isn't a 2d matrix! Verify the matrix and try again.")
                .isInstanceOf(MatrixMalformedException.class)
                .hasMessageContaining("This isn't a 2d matrix!");
    }

    @Test
    @DisplayName("It should throws a MatrixMalformedException, passing empty matrix")
    public void testFindPalindromes_WithEmptyMatrix_ShouldThrowMatrixMalformedException() {
        // Arrange
        char[][] matrix = {};

        // Act
        assertThat(catchThrowable(() -> matchService.findPalindromes(matrix)))
                .as("Invalid matrix size! Verify the matrix and try again.")
                .isInstanceOf(MatrixMalformedException.class)
                .hasMessageContaining("Invalid matrix size!");
    }

    @Test
    @DisplayName("It should throws a MatrixMalformedException, passing matrix with length greater than 10")
    public void testFindPalindromes_WithMatrixWithLengthGreatherThan10_ShouldThrowMatrixMalformedException() {
        // Arrange
        char[][] matrix = {
                {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'},
                {'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'},
                {'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'},
                {'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'},
                {'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'},
                {'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'},
                {'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'},
                {'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'},
                {'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'},
                {'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'}
        };

        // Act
        assertThat(catchThrowable(() -> matchService.findPalindromes(matrix)))
                .as("Invalid matrix size! Verify the matrix and try again.")
                .isInstanceOf(MatrixMalformedException.class)
                .hasMessageContaining("Invalid matrix size!");
    }

    @Test
    @DisplayName("It should return a empty list of palindromes, passing a single character matrix")
    public void testFindPalindromes_WithSingleCharacterMatrix_ShouldReturnEmptyList() throws MatrixMalformedException {
        // Arrange
        char[][] matrix = {{'a'}};

        // Act
        List<String> palindromes = matchService.findPalindromes(matrix);

        // Assert
        assertThat(palindromes).isEmpty();
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
        assertThat(palindromes).isEmpty();
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

    @Test
    @DisplayName("It should find all matches stored in db")
    public void testFindAllMatches_ReturnsAllMatches() {
        // Arrange
        List<MatchDto> expectedMatches = Instancio.ofList(MatchDto.class).size(10).create();
        when(persistence.getMatches()).thenReturn(expectedMatches);

        // Act
        List<MatchDto> actualMatches = matchService.findAllMatches();

        // Assert
        assertThat(expectedMatches).hasSize(actualMatches.size());
        verify(persistence, times(1)).getMatches();
    }

    @Test
    @DisplayName("It should return all matches that have incidence of these words")
    public void testFindMatchesByWordsIncidence_ReturnsMatchesByWord() {
        // Arrange
        ArrayList<String> words = new ArrayList<>();
        words.add("abba");

        List<MatchDto> expectedMatches = new ArrayList<>();

        // Instantiate manually for test coverage
        MatchDto matchDto = new MatchDto();
        expectedMatches.add(matchDto);

        when(persistence.getMatches(words)).thenReturn(expectedMatches);

        // Act
        List<MatchDto> actualMatches = matchService.findMatchesByWordIncidence(words);

        // Assert
        assertThat(expectedMatches).hasSize(actualMatches.size());
        verify(persistence, times(1)).getMatches(words);
    }
}
