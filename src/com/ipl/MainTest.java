package com.ipl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

class MainTest {
    List<Match> matches = Main.getMatchesForAllSeason();
    List<Delivery> deliveries = Main.getDeliveriesForAllSeasons();

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

    @Test
    void testGetDeliveriesForAllSeasons() {
        assertAll(
                () -> assertNotNull(deliveries),
                () -> assertEquals(150460, deliveries.size()),
                () -> assertEquals(1, deliveries.get(0).getMatchId()),
                () -> assertEquals("Royal Challengers Bangalore", deliveries.get(0).getBowlingTeam()),
                () -> assertEquals("TS Mills", deliveries.get(0).getBowler()),
                () -> assertEquals(0, deliveries.get(0).getExtraRuns()),
                () -> assertEquals("DA Warner", deliveries.get(0).getBatsman())
        );
    }

    @Test
    void testGetMatchesPlayedPerYear() {
        Map<Integer,Integer> matchesPlayedPerYear = Main.getMatchesPlayedPerYear(matches);
        assertAll(
                () -> assertFalse(matchesPlayedPerYear.isEmpty()),
                () -> assertFalse(matchesPlayedPerYear.containsKey(2018)),
                () -> assertEquals(10, matchesPlayedPerYear.size()),
                () -> assertEquals(58, matchesPlayedPerYear.get(2008)),
                () -> assertEquals(59, matchesPlayedPerYear.get(2017))
        );
    }
}