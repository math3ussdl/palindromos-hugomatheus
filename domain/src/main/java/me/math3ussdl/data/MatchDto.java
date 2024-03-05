package me.math3ussdl.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {

    private UUID id;
    private OffsetDateTime matchDate;
    private ArrayList<String> findWords;
}
