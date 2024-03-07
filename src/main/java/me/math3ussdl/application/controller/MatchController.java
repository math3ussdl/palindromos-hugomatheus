package me.math3ussdl.application.controller;

import me.math3ussdl.domain.data.MatchDto;
import me.math3ussdl.domain.port.api.MatchServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchServicePort service;

    @PostMapping("/findPalindromes")
    public ResponseEntity<?> findPalindromes(@RequestBody char[][] matrix) {

        List<String> palindromes = service.findPalindromes(matrix);
        return ResponseEntity.ok(palindromes);
    }

    @GetMapping
    public List<MatchDto> getMatches(@RequestParam(required = false) ArrayList<String> words) {

        List<MatchDto> matches;

        if (words == null || words.isEmpty()) {
            matches = service.findAllMatches();
        } else {
            matches = service.findMatchesByWordIncidence(words);
        }

        return matches;
    }
}
