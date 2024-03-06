package me.math3ussdl.controller;

import me.math3ussdl.data.MatchDto;
import me.math3ussdl.exception.MatrixMalformedException;
import me.math3ussdl.port.api.MatchServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchServicePort service;

    @PostMapping("/findPalindromes")
    public ResponseEntity<?> findPalindromes(@RequestBody char[][] matrix)
            throws MatrixMalformedException {

        try {
            List<String> palindromes = service.findPalindromes(matrix);
            return ResponseEntity.ok(palindromes);
        } catch (MatrixMalformedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred!");
        }
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
