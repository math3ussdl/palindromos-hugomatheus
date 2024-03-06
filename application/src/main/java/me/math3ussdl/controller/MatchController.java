package me.math3ussdl.controller;

import me.math3ussdl.data.MatchDto;
import me.math3ussdl.exception.MatrixMalformedException;
import me.math3ussdl.port.api.MatchServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchServicePort service;

    @PostMapping("/findPalindromes")
    public List<String> findPalindromes(@RequestBody char[][] matrix)
            throws MatrixMalformedException {

        return service.findPalindromes(matrix);
    }

    @GetMapping
    public List<MatchDto> getMatches(@RequestParam(required = false) String word) {

        List<MatchDto> matches;

        if (word == null) {
            matches = service.findAllMatches();
        } else {
            matches = service.findMatchesByWordIncidence(word);
        }

        return matches;
    }
}
