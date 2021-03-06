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
        Map<Integer, Integer> matchesPlayedPerYear = Main.getMatchesPlayedPerYear(matches);
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
        Map<String, Integer> matchesWonByAllTeamInAllYear = Main.getMatchesWonByAllTeamInAllYear(matches);
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

    @Test
    void testGetTopEconomicalBowlersIn2015() {
        List<Map.Entry<String, Float>> sortedEconomy = Main.getTopEconomicalBowlersIn2015(matches,deliveries);
        assertAll(
                () -> assertFalse(sortedEconomy.isEmpty()),
                () -> assertEquals("RN ten Doeschate", sortedEconomy.get(0).getKey()),
                () -> assertEquals("RN ten Doeschate=4.0", sortedEconomy.get(0).toString())
        );
    }

    @Test
    void testGetTossWonByAllTeamInAllYear() {
        Map<String, Integer> tossWonByAllTeamInAllYear = Main.getTossWonByAllTeamInAllYear(matches);
        assertAll(
                () -> assertEquals(14, tossWonByAllTeamInAllYear.size()),
                () -> assertFalse(tossWonByAllTeamInAllYear.containsKey("Team ABC")),
                () -> assertEquals(20, tossWonByAllTeamInAllYear.get("Pune Warriors"))
        );
    }

    @Test
    void testGetTotalManOfMatchForPlayerIn2016() {
        Map<String, Integer> totalManOfMatchForPlayerIn2016 = Main.getTotalManOfMatchForPlayerIn2016(matches);
        assertAll(
                () -> assertNotEquals(0, totalManOfMatchForPlayerIn2016.size()),
                () -> assertFalse(totalManOfMatchForPlayerIn2016.containsKey("Ravi")),
                () -> assertTrue(totalManOfMatchForPlayerIn2016.containsKey("M Vohra")),
                () -> assertEquals(5, totalManOfMatchForPlayerIn2016.get("V Kohli"))
        );
    }

    @Test
    void testGetNoOfSixesHitByDhoniInAllSeason() {
        Map<String, Integer> noOfSixesHitByDhoniInAllSeason = Main.getNoOfSixesHitByDhoniInAllSeason(deliveries);
        assertAll(
                () -> assertFalse(noOfSixesHitByDhoniInAllSeason.isEmpty()),
                () -> assertFalse(noOfSixesHitByDhoniInAllSeason.containsKey("V Kohli")),
                () -> assertTrue(noOfSixesHitByDhoniInAllSeason.containsKey("MS Dhoni")),
                () -> assertEquals(156, noOfSixesHitByDhoniInAllSeason.get("MS Dhoni"))
        );
    }

    @Test
    void testGetNoOfCatchesByFielderIn2015() {
        Map<String, Integer> noOfCatchesByFielderIn2015 = Main.getNoOfCatchesByFielderIn2015(matches,deliveries);
        assertAll(
                () -> assertFalse(noOfCatchesByFielderIn2015.isEmpty()),
                () -> assertFalse(noOfCatchesByFielderIn2015.containsKey("Ravi")),
                () -> assertTrue(noOfCatchesByFielderIn2015.containsKey("MS Dhoni")),
                () -> assertEquals(8, noOfCatchesByFielderIn2015.get("MS Dhoni"))
        );
    }

    @Test
    void testGetNoOfBoundariesHitByKohliInAllSeason() {
        Map<String, Integer> noOfBoundariesHitByKohliInAllSeason = Main.getNoOfBoundariesHitByKohliInAllSeason(deliveries);
        assertAll(
                () -> assertNotEquals(0, noOfBoundariesHitByKohliInAllSeason.size()),
                () -> assertEquals("{V Kohli=544}", noOfBoundariesHitByKohliInAllSeason.toString())
        );
    }

    @Test
    void testGetTop10WicketTakingBowlersInAllSeason() {
        List<Map.Entry<String, Integer>> top10WicketTakingBowlersInAllSeason = Main.getTop10WicketTakingBowlersInAllSeason(deliveries);
        assertAll(
                () -> assertEquals(10, top10WicketTakingBowlersInAllSeason.size()),
                () -> assertEquals(70, top10WicketTakingBowlersInAllSeason.get(9).getValue()),
                () -> assertEquals("A Mishra=97", top10WicketTakingBowlersInAllSeason.get(0).toString())
        );
    }
}