package me.math3ussdl.adapter;

import me.math3ussdl.data.MatchDto;
import me.math3ussdl.entity.Match;
import me.math3ussdl.repository.MatchRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchJpaAdapterTest {

    @Mock
    private MatchRepository repository;

    @InjectMocks
    private MatchJpaAdapter matchAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should saves a match successfully")
    public void testSaveMatch() {
        // Arrange
        List<String> palindromes = Arrays.asList("level", "radar", "madam");

        // Act
        matchAdapter.saveMatch(palindromes);

        // Assert
        verify(repository, times(1)).save(any(Match.class));
    }

    @Test
    @DisplayName("It should saves a match successfully")
    public void testGetMatches() {
        // Arrange
        List<Match> matches = Instancio.ofList(Match.class).size(5).create();
        when(repository.findAll()).thenReturn(matches);

        // Act
        List<MatchDto> result = matchAdapter.getMatches();

        // Assert
        assertThat(matches.size()).isEqualTo(result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("It should return all matches containing searched words from list")
    public void testGetMatchesWithSearchedWords() {
        // Arrange
        ArrayList<String> searchedWords = new ArrayList<>(Arrays.asList("level", "radar", "madam"));
        List<Match> matches = Instancio.ofList(Match.class).size(5).create();
        when(repository.findByFindWordsContaining(String.join(",", searchedWords))).thenReturn(matches);

        // Act
        List<MatchDto> result = matchAdapter.getMatches(searchedWords);

        // Assert
        assertThat(matches.size()).isEqualTo(result.size());
        verify(repository, times(1))
                .findByFindWordsContaining(String.join(",", searchedWords));
    }
}
