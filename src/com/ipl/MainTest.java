package com.ipl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class MainTest {
    List<Match> matches = Main.getMatchesForAllSeason();

    @Test
    void testGetMatchesForAllSeason() {
        assertAll(
                () -> assertNotNull(matches),
                () -> assertEquals(636, matches.size()),
                () -> assertEquals(1, matches.get(0).getId()),
                () -> assertEquals(2016, matches.get(matches.size()-1).getSeason()),
                () -> assertEquals("Sunrisers Hyderabad", matches.get(0).getWinner()),
                () -> assertEquals("Royal Challengers Bangalore", matches.get(0).getTossWinner()),
                () -> assertEquals("Yuvraj Singh", matches.get(0).getPlayerOfMatch())
        );
    }
}