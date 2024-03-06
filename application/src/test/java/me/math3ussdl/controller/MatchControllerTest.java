package me.math3ussdl.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import me.math3ussdl.data.MatchDto;
import me.math3ussdl.exception.MatrixMalformedException;
import me.math3ussdl.port.api.MatchServicePort;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchControllerTest {

    @Mock
    private MatchServicePort matchService;

    @InjectMocks
    private MatchController matchController;

    @Test
    @DisplayName("It should return a ok response with list of palindromes, passing a valid matrix")
    public void testFindPalindromes_ValidMatrix_ReturnsListOfPalindromes() throws MatrixMalformedException {
        // Arrange
        char[][] matrix = {
                {'a', 'b', 'b', 'a'},
                {'d', 'e', 'f', 's'},
                {'e', 'h', 'h', 'e'},
                {'a', 'h', 'i', 'a'}
        };
        List<String> expectedPalindromes = new ArrayList<>();
        expectedPalindromes.add("abba");
        expectedPalindromes.add("ehhe");

        when(matchService.findPalindromes(matrix)).thenReturn(expectedPalindromes);

        // Act
        ResponseEntity<?> response = matchController.findPalindromes(matrix);

        // Assert
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat(expectedPalindromes).isEqualTo(response.getBody());
        verify(matchService, times(1)).findPalindromes(matrix);
    }

    @Test
    @DisplayName("It should throws MatrixMalformedException, returning bad request response")
    public void testFindPalindromes_MatrixMalformedException_ReturnsBadRequestResponse()
            throws MatrixMalformedException {

        // Arrange
        char[][] matrix = {{'a', 'b'}, {'c', 'd', 'e'}};
        String errorMessage = "Matrix is malformed!";
        when(matchService.findPalindromes(matrix)).thenThrow(new MatrixMalformedException(errorMessage));

        // Act
        ResponseEntity<?> response = matchController.findPalindromes(matrix);

        // Assert
        assertThat(HttpStatus.BAD_REQUEST).isEqualTo(response.getStatusCode());
        assertThat(errorMessage).isEqualTo(response.getBody());
        verify(matchService, times(1)).findPalindromes(matrix);
    }

    @Test
    @DisplayName("It should throws Exception, returning internal server error response")
    public void testFindPalindromes_ExceptionThrown_ReturnsInternalServerErrorResponse() {
        // Arrange
        char[][] matrix = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}};
        when(matchService.findPalindromes(matrix)).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<?> response = matchController.findPalindromes(matrix);

        // Assert
        assertThat(HttpStatus.INTERNAL_SERVER_ERROR).isEqualTo(response.getStatusCode());
        assertThat("An unexpected error occurred!").isEqualTo(response.getBody());
        verify(matchService, times(1)).findPalindromes(matrix);
    }

    @Test
    @DisplayName("It should return all matches, passing null on word list")
    public void testGetMatches_NullWordListProvided_ReturnsAllMatches() {
        // Arrange
        List<MatchDto> expectedMatches = new ArrayList<>();
        expectedMatches.add(new MatchDto());
        expectedMatches.add(new MatchDto());

        when(matchService.findAllMatches()).thenReturn(expectedMatches);

        // Act
        List<MatchDto> actualMatches = matchController.getMatches(null);

        // Assert
        assertThat(expectedMatches).isEqualTo(actualMatches);
        verify(matchService, times(1)).findAllMatches();
    }

    @Test
    @DisplayName("It should return all matches, passing empty word list")
    public void testGetMatches_EmptyWordListProvided_ReturnsAllMatches() {
        // Arrange
        List<MatchDto> expectedMatches = new ArrayList<>();
        expectedMatches.add(new MatchDto());
        expectedMatches.add(new MatchDto());

        when(matchService.findAllMatches()).thenReturn(expectedMatches);

        // Act
        List<MatchDto> actualMatches = matchController.getMatches(new ArrayList<>());

        // Assert
        assertThat(expectedMatches).isEqualTo(actualMatches);
        verify(matchService, times(1)).findAllMatches();
    }

    @Test
    @DisplayName("It should return all matches, passing words")
    public void testGetMatches_WordsProvided_ReturnsMatchesByWordIncidence() {
        // Arrange
        ArrayList<String> words = new ArrayList<>();
        words.add("word1");
        words.add("word2");

        List<MatchDto> expectedMatches = new ArrayList<>();
        expectedMatches.add(new MatchDto(UUID.randomUUID(), OffsetDateTime.now(), "word1,word2"));

        when(matchService.findMatchesByWordIncidence(words)).thenReturn(expectedMatches);

        // Act
        List<MatchDto> actualMatches = matchController.getMatches(words);

        // Assert
        assertThat(expectedMatches).isEqualTo(actualMatches);
        verify(matchService, times(1)).findMatchesByWordIncidence(words);
    }
}
