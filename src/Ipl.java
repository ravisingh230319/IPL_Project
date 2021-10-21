import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Ipl {
    static HashMap<Integer, Integer> matchesPerYear = new HashMap<>();
    static HashMap<String, Integer> matchesWonByAllTeamInAllYear = new HashMap<>();
    static ArrayList<String> matchId2016 = new ArrayList<>();
    static HashMap<String, Integer> extraRunsConcededIn2016 = new HashMap<>();
    static ArrayList<String> matchId2015 = new ArrayList<>();
    static HashMap<String, Integer> totalRuns = new HashMap<>();
    static HashMap<String, Integer> legalDelivery = new HashMap<>();
    static HashMap<String, Float> economy = new HashMap<>();
    static HashMap<String, Integer> tossWonByAllTeamInAllYear = new HashMap<>();
    static HashMap<String, Integer> totalManOfMatchForPlayerIn2016 = new HashMap<>();
    static HashMap<String, Integer> noOfSixesHitByDhoniInAllSeason = new HashMap<>();
    static HashMap<String, Integer> noOfCatchesByFielderIn2015 = new HashMap<>();
    static HashMap<String, Integer> noOfBoundariesHitByKohliInAllSeason = new HashMap<>();
    static HashMap<String, Integer> top10WicketTakingBowlersInAllSeason = new HashMap<>();


    public static void getMatchesPerYear(String matchesLine){
        String[] match;
        match = matchesLine.split(",");
        int year = Integer.parseInt(match[1]);

        if(matchesPerYear.containsKey(year)) {
            matchesPerYear.put(year, matchesPerYear.get(year) + 1);
        }
        else {
            matchesPerYear.put(year, 1);
        }
    }

    public static void getMatchesWonByAllTeamInAllYear(String matchesLine) {
        String[] match;
        match = matchesLine.split(",");
        String winnerTeam = match[10];

        if(!winnerTeam.equals("") && matchesWonByAllTeamInAllYear.containsKey(winnerTeam)) {
            matchesWonByAllTeamInAllYear.put(winnerTeam, matchesWonByAllTeamInAllYear.get(winnerTeam) + 1);
        }
        else if(!winnerTeam.equals("")) {
            matchesWonByAllTeamInAllYear.put(winnerTeam, 1);
        }
    }

    public static void getExtraRunsConcededPerTeamIn2016(ArrayList<String> matchesId, String deliveriesLine){
        String[] delivery;
        delivery = deliveriesLine.split(",");
        String bowlingTeam = delivery[3];
        int extraRunsConceded = Integer.parseInt(delivery[16]);

        if(matchesId.contains(delivery[0]))
        {
            if(extraRunsConcededIn2016.containsKey(bowlingTeam)) {
                extraRunsConcededIn2016.put(bowlingTeam, extraRunsConcededIn2016.get(bowlingTeam) + extraRunsConceded);
            }
            else {
                extraRunsConcededIn2016.put(bowlingTeam, extraRunsConceded);
            }
        }
    }

    public static void topEconomicalBowlersIn2015(ArrayList<String> matchesId, String deliveriesLine)
    {
        String[] delivery;
        delivery = deliveriesLine.split(",");

        if(matchesId.contains(delivery[0]))
        {
            int wideBall = Integer.parseInt(delivery[10]);
            int noBall = Integer.parseInt(delivery[13]);
            int runs = Integer.parseInt(delivery[17]);
            String bowlerName = delivery[8];
            if(legalDelivery.containsKey(bowlerName)) {
                if(wideBall == 0 && noBall == 0)
                    legalDelivery.put(bowlerName, legalDelivery.get(bowlerName) + 1);
            }
            else
                legalDelivery.put(bowlerName, 1);

            if(totalRuns.containsKey(bowlerName))
            {
                totalRuns.put(bowlerName, totalRuns.get(bowlerName) + runs);
            }
            else{
                totalRuns.put(bowlerName, runs);
            }
            economy.put(bowlerName, totalRuns.get(bowlerName) * 6f / legalDelivery.get(bowlerName));
        }
    }

    public static void getTossWonByAllTeamInAllYear(String matchesLine) {
        String[] match;
        match = matchesLine.split(",");
        String tossWinningTeam = match[6];

        if(tossWonByAllTeamInAllYear.containsKey(tossWinningTeam)){
            tossWonByAllTeamInAllYear.put(tossWinningTeam, tossWonByAllTeamInAllYear.get(tossWinningTeam) + 1);
        }
        else
        {
            tossWonByAllTeamInAllYear.put(tossWinningTeam, 1);
        }
    }

    public static void getTotalManOfMatchForPlayerIn2016(String matchesLine) {
        String[] match;
        match = matchesLine.split(",");
        String playerOfMatch = match[13];
        int year = Integer.parseInt(match[1]);

        if(year == 2016 && !playerOfMatch.equals("")) {
            if (totalManOfMatchForPlayerIn2016.containsKey(playerOfMatch)) {
                totalManOfMatchForPlayerIn2016.put(playerOfMatch, totalManOfMatchForPlayerIn2016.get(playerOfMatch) + 1);
            } else {
                totalManOfMatchForPlayerIn2016.put(playerOfMatch, 1);
            }
        }
    }

    public static void getNoOfSixesHitByDhoniInAllSeason(String deliveriesLine){
        String[] delivery;
        delivery = deliveriesLine.split(",");
        int batsmanRuns = Integer.parseInt(delivery[15]);
        String batsmanName = delivery[6];

        if(batsmanRuns == 6 && batsmanName.equals("MS Dhoni")) {
            if (noOfSixesHitByDhoniInAllSeason.containsKey("MS Dhoni")) {
                noOfSixesHitByDhoniInAllSeason.put("MS Dhoni", noOfSixesHitByDhoniInAllSeason.get("MS Dhoni") + 1);
            } else {
                noOfSixesHitByDhoniInAllSeason.put("MS Dhoni", 1);
            }
        }
    }

    public static void getNoOfCatchesByFielderIn2015(ArrayList<String> matchesId, String deliveriesLine){
        String[] delivery;
        delivery = deliveriesLine.split(",");
        String dismissalKind = "";
        String fielder = "";
        if(delivery.length > 20){
            dismissalKind = delivery[19];
            fielder = delivery[20];
        }

        if(dismissalKind.equals("caught") && matchesId.contains(delivery[0])) {
            if (noOfCatchesByFielderIn2015.containsKey(fielder)) {
                noOfCatchesByFielderIn2015.put(fielder, noOfCatchesByFielderIn2015.get(fielder) + 1);
            } else {
                noOfCatchesByFielderIn2015.put(fielder, 1);
            }
        }
    }

    public static void getNoOfBoundariesHitByKohliInAllSeason(String deliveriesLine){
        String[] delivery;
        delivery = deliveriesLine.split(",");
        int batsmanRuns = Integer.parseInt(delivery[15]);
        String batsmanName = delivery[6];

        if((batsmanRuns == 6 || batsmanRuns == 4) && batsmanName.equals("V Kohli")) {
            if (noOfBoundariesHitByKohliInAllSeason.containsKey("V Kohli")) {
                noOfBoundariesHitByKohliInAllSeason.put("V Kohli", noOfBoundariesHitByKohliInAllSeason.get("V Kohli") + 1);
            } else {
                noOfBoundariesHitByKohliInAllSeason.put("V Kohli", 1);
            }
        }
    }

    public static void getTop10WicketTakingBowlersInAllSeason(String deliveriesLine){
        String[] delivery;
        delivery = deliveriesLine.split(",");
        String dismissalKind = "";
        String bowlersName = delivery[8];
        if(delivery.length > 20){
            dismissalKind = delivery[19];
        }

        if(!dismissalKind.equals("run out") && !dismissalKind.equals("")) {
            if (top10WicketTakingBowlersInAllSeason.containsKey(bowlersName)) {
                top10WicketTakingBowlersInAllSeason.put(bowlersName, top10WicketTakingBowlersInAllSeason.get(bowlersName) + 1);
            } else {
                top10WicketTakingBowlersInAllSeason.put(bowlersName, 1);
            }
        }
    }

    public static void main(String[] args){
        String matchesPath = "/home/ravi/Mountblue/IPL_Project/input/matches.csv";
        String deliveriesPath = "/home/ravi/Mountblue/IPL_Project/input/deliveries.csv";
        String matchesLine;
        String deliveriesLine;

        try {
            BufferedReader matchesBufferedReader = new BufferedReader(new FileReader(matchesPath));
            BufferedReader deliveriesBufferedReader = new BufferedReader(new FileReader(deliveriesPath));
            matchesBufferedReader.readLine();
            deliveriesBufferedReader.readLine();

            while((matchesLine = matchesBufferedReader.readLine()) != null) {
                if((matchesLine.split(","))[1].equals("2016")) {
                    matchId2016.add((matchesLine.split(","))[0]);
                }
                if((matchesLine.split(","))[1].equals("2015")) {
                    matchId2015.add((matchesLine.split(","))[0]);
                }
                getMatchesPerYear(matchesLine);
                getMatchesWonByAllTeamInAllYear(matchesLine);
                getTossWonByAllTeamInAllYear(matchesLine);
                getTotalManOfMatchForPlayerIn2016(matchesLine);
            }
            while((deliveriesLine = deliveriesBufferedReader.readLine()) != null)
            {
                getExtraRunsConcededPerTeamIn2016(matchId2016, deliveriesLine);
                topEconomicalBowlersIn2015(matchId2015, deliveriesLine);
                getNoOfSixesHitByDhoniInAllSeason(deliveriesLine);
                getNoOfCatchesByFielderIn2015(matchId2015, deliveriesLine);
                getNoOfBoundariesHitByKohliInAllSeason(deliveriesLine);
                getTop10WicketTakingBowlersInAllSeason(deliveriesLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map.Entry<String, Float>> sortedEconomy = new ArrayList<>(economy.entrySet());
        sortedEconomy.sort((economy1, economy2) -> economy1.getValue().compareTo(economy2.getValue()));
        List<Map.Entry<String, Integer>> sortedWickets = new ArrayList<>(top10WicketTakingBowlersInAllSeason.entrySet());
        sortedWickets.sort((wicket1, wicket2) -> wicket2.getValue().compareTo(wicket1.getValue()));

        System.out.println();
        System.out.println("1. Number of matches played per year of all the years in IPL.");
        System.out.println(matchesPerYear);
        System.out.println();
        System.out.println("2. Number of matches won of all teams over all the years of IPL.");
        System.out.println(matchesWonByAllTeamInAllYear);
        System.out.println();
        System.out.println("3. For the year 2016 get the extra runs conceded per team.");
        System.out.println(extraRunsConcededIn2016);
        System.out.println();
        System.out.println("4. For the year 2015 get the top economical bowlers.");
        System.out.println(sortedEconomy);
        System.out.println();
        System.out.println("5. Number of toss won of all teams over all the years of IPL.");
        System.out.println(tossWonByAllTeamInAllYear);
        System.out.println();
        System.out.println("6. Total no of man of the matches for players in 2016.");
        System.out.println(totalManOfMatchForPlayerIn2016);
        System.out.println();
        System.out.println("7. No of sixes hit by Dhoni in all seasons.");
        System.out.println(noOfSixesHitByDhoniInAllSeason);
        System.out.println();
        System.out.println("8. No of catches by fielders in 2015.");
        System.out.println(noOfCatchesByFielderIn2015);
        System.out.println();
        System.out.println("9. No of boundaries hit by Kohli in all seasons.");
        System.out.println(noOfBoundariesHitByKohliInAllSeason);
        System.out.println();
        System.out.println("10. Top 10 wicket takers of all season.");
        System.out.print("{");
        for(int index = 0; index < 10; index++)
        {
            System.out.print(sortedWickets.get(index));
            if(index < 9){
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }
}
