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

    @Test
    void testGetMatchesWonByAllTeamInAllYear() {
        Map<String,Integer> matchesWonByAllTeamInAllYear = Main.getMatchesWonByAllTeamInAllYear(matches);
        assertAll(
                () -> assertFalse(matchesWonByAllTeamInAllYear.isEmpty()),
                () -> assertEquals(14, matchesWonByAllTeamInAllYear.size()),
                () -> assertFalse(matchesWonByAllTeamInAllYear.containsKey("Bengal Tigers")),
                () -> assertEquals(92, matchesWonByAllTeamInAllYear.get("Mumbai Indians"))
        );
    }

    @Test
    void testGetExtraRunsConcededPerTeamIn2016() {
        Map<String, Integer> extraRunsConcededPerTeamIn2016 = Main.getExtraRunsConcededPerTeamIn2016(matches,deliveries);
        assertAll(
                () -> assertFalse(extraRunsConcededPerTeamIn2016.isEmpty()),
                () -> assertFalse(extraRunsConcededPerTeamIn2016.containsKey("Bengal Tigers")),
                () -> assertEquals(8, extraRunsConcededPerTeamIn2016.size()),
                () -> assertEquals(98, extraRunsConcededPerTeamIn2016.get("Gujarat Lions"))
        );
    }
}