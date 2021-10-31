package com.ipl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final int ID = 0;
    private static final int SEASON = 1;
    private static final int TOSS_WINNER = 6;
    private static final int WINNER = 10;
    private static final int PLAYER_OF_MATCH = 13;
    private static final int MATCH_ID = 0;
    private static final int BOWLING_TEAM = 3;
    private static final int BATSMAN = 6;
    private static final int BOWLER = 8;
    private static final int WIDE_RUNS = 10;
    private static final int NO_BALL_RUNS = 13;
    private static final int BATSMAN_RUNS = 15;
    private static final int EXTRA_RUNS = 16;
    private static final int TOTAL_RUNS = 17;
    private static final int DISMISSAL_KIND = 19;
    private static final int FIELDER = 20;

    public static void main(String args[]) {
        List<Match> matches = getMatchesForAllSeason();
        List<Delivery> deliveries = getDeliveriesForAllSeasons();
        System.out.println("\n1. Number of matches played per year of all the years in IPL.");
        System.out.println(getMatchesPlayedPerYear(matches));
        System.out.println("\n2. Number of matches won of all teams over all the years of IPL.");
        System.out.println(getMatchesWonByAllTeamInAllYear(matches));
        System.out.println("\n3. For the year 2016 get the extra runs conceded per team.");
        System.out.println(getExtraRunsConcededPerTeamIn2016(matches, deliveries));
        System.out.println("\n4. For the year 2015 get the top economical bowlers.");
        System.out.println(getTopEconomicalBowlersIn2015(matches, deliveries));
        System.out.println("\n5. Number of toss won of all teams over all the years of IPL.");
        System.out.println(getTossWonByAllTeamInAllYear(matches));
        System.out.println("\n6. Total no of man of the matches for players in 2016.");
        System.out.println(getTotalManOfMatchForPlayerIn2016(matches));
        System.out.println("\n7. No of sixes hit by Dhoni in all seasons.");
        System.out.println(getNoOfSixesHitByDhoniInAllSeason(deliveries));
        System.out.println("\n8. No of catches by fielders in 2015.");
        System.out.println(getNoOfCatchesByFielderIn2015(matches, deliveries));
        System.out.println("\n9. No of boundaries hit by Kohli in all seasons.");
        System.out.println(getNoOfBoundariesHitByKohliInAllSeason(deliveries));
        System.out.println("\n10. Top 10 wicket takers of all season.");
        System.out.println(getTop10WicketTakingBowlersInAllSeason(deliveries));
    }

    public static List<Match> getMatchesForAllSeason() {
        List<Match> matches = new ArrayList<>();
        String readMatchesLine;

        try {
            BufferedReader matchesBufferedReader = new BufferedReader(new FileReader("com/ipl/dataset/matches.csv"));
            matchesBufferedReader.readLine();

            while ((readMatchesLine = matchesBufferedReader.readLine()) != null) {
                Match match = new Match();
                String[] matchesLineArray = readMatchesLine.split(",");
                match.setId(Integer.parseInt(matchesLineArray[ID]));
                match.setSeason(Integer.parseInt(matchesLineArray[SEASON]));
                match.setTossWinner(matchesLineArray[TOSS_WINNER]);
                match.setWinner(matchesLineArray[WINNER]);
                match.setPlayerOfMatch(matchesLineArray[PLAYER_OF_MATCH]);
                matches.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public static List<Delivery> getDeliveriesForAllSeasons() {
        List<Delivery> deliveries = new ArrayList<>();
        String readDeliveriesLine;

        try {
            BufferedReader deliveriesBufferedReader = new BufferedReader(new FileReader("com/ipl/dataset/deliveries.csv"));
            deliveriesBufferedReader.readLine();

            while ((readDeliveriesLine = deliveriesBufferedReader.readLine()) != null) {
                Delivery delivery = new Delivery();
                String[] deliveriesLineArray = readDeliveriesLine.split(",");
                delivery.setMatchId(Integer.parseInt(deliveriesLineArray[MATCH_ID]));
                delivery.setBowlingTeam(deliveriesLineArray[BOWLING_TEAM]);
                delivery.setBowler(deliveriesLineArray[BOWLER]);
                delivery.setWideRuns(Integer.parseInt(deliveriesLineArray[WIDE_RUNS]));
                delivery.setNoballRuns(Integer.parseInt(deliveriesLineArray[NO_BALL_RUNS]));
                delivery.setExtraRuns(Integer.parseInt(deliveriesLineArray[EXTRA_RUNS]));
                delivery.setTotalRuns(Integer.parseInt(deliveriesLineArray[TOTAL_RUNS]));
                delivery.setBatsman(deliveriesLineArray[BATSMAN]);
                delivery.setBatsmanRuns(Integer.parseInt(deliveriesLineArray[BATSMAN_RUNS]));
                if(deliveriesLineArray.length>20){
                    delivery.setDismissalKind(deliveriesLineArray[DISMISSAL_KIND]);
                    delivery.setFielder(deliveriesLineArray[FIELDER]);
                }
                else {
                    delivery.setDismissalKind("");
                    delivery.setFielder("");
                }
                deliveries.add(delivery);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    public static Map<Integer, Integer> getMatchesPlayedPerYear(List<Match> matches) {
        Map<Integer, Integer> matchesPlayedPerYear = new TreeMap<>();

        for(Match match : matches){
            if (matchesPlayedPerYear.containsKey(match.getSeason())) {
                matchesPlayedPerYear.put(match.getSeason(), matchesPlayedPerYear.get(match.getSeason()) + 1);
            } else {
                matchesPlayedPerYear.put(match.getSeason(), 1);
            }
        }
        return matchesPlayedPerYear;
    }

    public static Map<String, Integer> getMatchesWonByAllTeamInAllYear(List<Match> matches) {
        Map<String, Integer> matchesWonByAllTeamInAllYear = new HashMap<>();

        for(Match match : matches){
            if(!match.getWinner().equals("")) {
                if (matchesWonByAllTeamInAllYear.containsKey(match.getWinner())) {
                    matchesWonByAllTeamInAllYear.put(match.getWinner(), matchesWonByAllTeamInAllYear.get(match.getWinner()) + 1);
                } else {
                    matchesWonByAllTeamInAllYear.put(match.getWinner(), 1);
                }
            }
        }
        return matchesWonByAllTeamInAllYear;
    }

    public static Map<String, Integer> getExtraRunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
        List<Integer> matchIdIn2016 = new ArrayList<>();
        Map<String, Integer> extraRunsConcededPerTeamIn2016 = new HashMap<>();

        for(Match match : matches){
            if(match.getSeason() == 2016) {
                matchIdIn2016.add(match.getId());
            }
        }
        for (Delivery delivery : deliveries) {
            if (matchIdIn2016.contains(delivery.getMatchId())) {
                if (extraRunsConcededPerTeamIn2016.containsKey(delivery.getBowlingTeam())) {
                    extraRunsConcededPerTeamIn2016.put(delivery.getBowlingTeam(), extraRunsConcededPerTeamIn2016.get(delivery.getBowlingTeam()) + delivery.getExtraRuns());
                } else {
                    extraRunsConcededPerTeamIn2016.put(delivery.getBowlingTeam(), delivery.getExtraRuns());
                }
            }
        }
        return extraRunsConcededPerTeamIn2016;
    }

    public static List<Map.Entry<String, Float>> getTopEconomicalBowlersIn2015(List<Match> matches, List<Delivery> deliveries) {
        List<Integer> matchIdIn2015 = new ArrayList<>();
        Map<String, Integer> legalDeliveries = new HashMap<>();
        Map<String, Integer> totalRuns = new HashMap<>();
        Map<String, Float> unsortedEconomy = new HashMap<>();
        List<Map.Entry<String, Float>> sortedEconomy;

        for(Match match : matches){
            if(match.getSeason() == 2015) {
                matchIdIn2015.add(match.getId());
            }
        }
        for (Delivery delivery : deliveries) {
            if (matchIdIn2015.contains(delivery.getMatchId())) {
                if (legalDeliveries.containsKey(delivery.getBowler()) && totalRuns.containsKey(delivery.getBowler())) {
                    if (delivery.getWideRuns() == 0 && delivery.getNoballRuns() == 0) {
                        legalDeliveries.put(delivery.getBowler(), legalDeliveries.get(delivery.getBowler()) + 1);
                    }
                    totalRuns.put(delivery.getBowler(), totalRuns.get(delivery.getBowler()) + delivery.getTotalRuns());
                } else {
                    legalDeliveries.put(delivery.getBowler(), 1);
                    totalRuns.put(delivery.getBowler(), delivery.getTotalRuns());
                }
                unsortedEconomy.put(delivery.getBowler(), totalRuns.get(delivery.getBowler()) * 6f / legalDeliveries.get(delivery.getBowler()));
            }
        }
        sortedEconomy = new ArrayList<>(unsortedEconomy.entrySet());
        sortedEconomy.sort((economy1, economy2) -> economy1.getValue().compareTo(economy2.getValue()));
        return sortedEconomy;
    }

    public static Map<String, Integer> getTossWonByAllTeamInAllYear(List<Match> matches) {
        Map<String, Integer> tossWonByAllTeamInAllYear = new HashMap<>();

        for(Match match : matches){
            if (tossWonByAllTeamInAllYear.containsKey(match.getTossWinner())) {
                tossWonByAllTeamInAllYear.put(match.getTossWinner(), tossWonByAllTeamInAllYear.get(match.getTossWinner()) + 1);
            } else {
                tossWonByAllTeamInAllYear.put(match.getTossWinner(), 1);
            }
        }
        return tossWonByAllTeamInAllYear;
    }

    public static Map<String, Integer> getTotalManOfMatchForPlayerIn2016(List<Match> matches) {
        List<Integer> matchIdIn2016 = new ArrayList<>();
        Map<String, Integer> totalManOfMatchForPlayerIn2016 = new HashMap<>();

        for(Match match : matches){
            if(match.getSeason() == 2016 && !match.getPlayerOfMatch().equals("")) {
                matchIdIn2016.add(match.getId());
                if (totalManOfMatchForPlayerIn2016.containsKey(match.getPlayerOfMatch())) {
                    totalManOfMatchForPlayerIn2016.put(match.getPlayerOfMatch(), totalManOfMatchForPlayerIn2016.get(match.getPlayerOfMatch()) + 1);
                } else {
                    totalManOfMatchForPlayerIn2016.put(match.getPlayerOfMatch(), 1);
                }
            }

        }
        return totalManOfMatchForPlayerIn2016;
    }

    public static Map<String, Integer> getNoOfSixesHitByDhoniInAllSeason(List<Delivery> deliveries) {
        Map<String, Integer> noOfSixesHitByDhoniInAllSeason = new HashMap<>();
        final String DHONI = "MS Dhoni";

        for (Delivery delivery : deliveries) {
            if (delivery.getBatsmanRuns() == 6 && delivery.getBatsman().equals(DHONI)) {
                if (noOfSixesHitByDhoniInAllSeason.containsKey(DHONI)) {
                    noOfSixesHitByDhoniInAllSeason.put(DHONI, noOfSixesHitByDhoniInAllSeason.get(DHONI) + 1);
                } else {
                    noOfSixesHitByDhoniInAllSeason.put(DHONI, 1);
                }
            }
        }
        return noOfSixesHitByDhoniInAllSeason;
    }

    public static Map<String, Integer> getNoOfCatchesByFielderIn2015(List<Match> matches, List<Delivery> deliveries) {
        List<Integer> matchIdIn2015 = new ArrayList<>();
        Map<String, Integer> noOfCatchesByFielderIn2015 = new HashMap<>();

        for(Match match : matches){
            if(match.getSeason() == 2015) {
                matchIdIn2015.add(match.getId());
            }
        }
        for (Delivery delivery : deliveries) {
            if (delivery.getDismissalKind().equals("caught") && matchIdIn2015.contains(delivery.getMatchId())) {
                if (noOfCatchesByFielderIn2015.containsKey(delivery.getFielder())) {
                    noOfCatchesByFielderIn2015.put(delivery.getFielder(), noOfCatchesByFielderIn2015.get(delivery.getFielder()) + 1);
                } else {
                    noOfCatchesByFielderIn2015.put(delivery.getFielder(), 1);
                }
            }
        }
        return noOfCatchesByFielderIn2015;
    }

    public static Map<String, Integer> getNoOfBoundariesHitByKohliInAllSeason(List<Delivery> deliveries) {
        Map<String, Integer> noOfBoundariesHitByKohliInAllSeason = new HashMap<>();
        final String KOHLI = "V Kohli";

        for (Delivery delivery : deliveries) {
            if ((delivery.getBatsmanRuns() == 6 || delivery.getBatsmanRuns() == 4) && delivery.getBatsman().equals(KOHLI)) {
                if (noOfBoundariesHitByKohliInAllSeason.containsKey(KOHLI)) {
                    noOfBoundariesHitByKohliInAllSeason.put(KOHLI, noOfBoundariesHitByKohliInAllSeason.get(KOHLI) + 1);
                } else {
                    noOfBoundariesHitByKohliInAllSeason.put(KOHLI, 1);
                }
            }
        }
        return noOfBoundariesHitByKohliInAllSeason;
    }

    public static List<Map.Entry<String, Integer>> getTop10WicketTakingBowlersInAllSeason(List<Delivery> deliveries) {
        Map<String, Integer> unsortedWickets = new HashMap<>();
        List<Map.Entry<String, Integer>> top10WicketTakingBowlersInAllSeason;

        for (Delivery delivery : deliveries) {
            if (!delivery.getDismissalKind().equals("run out") && !delivery.getDismissalKind().equals("")) {
                if (unsortedWickets.containsKey(delivery.getBowler())) {
                    unsortedWickets.put(delivery.getBowler(), unsortedWickets.get(delivery.getBowler()) + 1);
                } else {
                    unsortedWickets.put(delivery.getBowler(), 1);
                }
            }
        }
        top10WicketTakingBowlersInAllSeason = new ArrayList<>(unsortedWickets.entrySet());
        top10WicketTakingBowlersInAllSeason.sort((wicket1, wicket2) -> wicket2.getValue().compareTo(wicket1.getValue()));
        while(top10WicketTakingBowlersInAllSeason.size() > 10){
            top10WicketTakingBowlersInAllSeason.remove(top10WicketTakingBowlersInAllSeason.size()-1);
        }
        return top10WicketTakingBowlersInAllSeason;
    }
}