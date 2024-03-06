package me.math3ussdl.controller;

import me.math3ussdl.exception.MatrixMalformedException;
import me.math3ussdl.port.api.MatchServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
