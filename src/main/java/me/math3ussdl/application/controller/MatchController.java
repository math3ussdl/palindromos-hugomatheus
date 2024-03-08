package me.math3ussdl.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary =
            "By passing a square matrix of up to 10 rows and 10 columns, the API will return a list of words that are palindromic.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found list of palindromes",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            type = "array",
                                            example = "[\"OSSO\",\n" +
                                                    "    \"YJJY\",\n" +
                                                    "    \"LPPL\",\n" +
                                                    "    \"ARARA\"]"
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid 2d matrix supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An unexpected error occurred!",
                    content = @Content
            )
    })
    @PostMapping("/findPalindromes")
    public ResponseEntity<?> findPalindromes(@RequestBody char[][] matrix) {

        List<String> palindromes = service.findPalindromes(matrix);
        return ResponseEntity.ok(palindromes);
    }

    @Operation(summary = "Retrieve matches based on provided words.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved matches.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = MatchDto.class)
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An unexpected error occurred!",
                    content = @Content
            )
    })
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
