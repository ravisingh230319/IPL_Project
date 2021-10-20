import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Ipl {
    static HashMap<Integer, Integer> matchesPerYear = new HashMap<>();
    static HashMap<String,Integer> matchesWonByAllTeamInAllYear = new HashMap<>();
    static ArrayList<String> matchId2016 = new ArrayList<>();
    static HashMap<String,Integer> extraRunsConcededIn2016 = new HashMap<>();
    static ArrayList<String> matchId2015 = new ArrayList<>();
    static HashMap<String,Integer> totalRuns = new HashMap<>();
    static HashMap<String,Integer> legalDelivery = new HashMap<>();
    static HashMap<String,Float> economy = new HashMap<>();

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
                getMatchesPerYear(matchesLine);
                getMatchesWonByAllTeamInAllYear(matchesLine);
                if((matchesLine.split(","))[1].equals("2016")) {
                    matchId2016.add((matchesLine.split(","))[0]);
                }
                if((matchesLine.split(","))[1].equals("2015")){
                    matchId2015.add((matchesLine.split(","))[0]);
                }
            }
            while((deliveriesLine = deliveriesBufferedReader.readLine()) != null)
            {
                getExtraRunsConcededPerTeamIn2016(matchId2016, deliveriesLine);
                topEconomicalBowlersIn2015(matchId2015, deliveriesLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map.Entry<String,Float>> sortedEconomy = new ArrayList<>(economy.entrySet());
        sortedEconomy.sort((economy1, economy2) -> economy1.getValue().compareTo(economy2.getValue()));
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

    }
}
