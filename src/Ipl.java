import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Ipl {
    static HashMap<Integer, Integer> matchesPerYear = new HashMap<>();
    static HashMap<String,Integer> matchesWonByAllTeamInAllYear = new HashMap<>();

    public static void getMatchesPerYear(String matchesLine){
        String[] match;
        match = matchesLine.split(",");
        int year = Integer.parseInt(match[1]);

        if(matchesPerYear.containsKey(year))
            matchesPerYear.put(year, matchesPerYear.get(year) + 1);
        else
            matchesPerYear.put(year, 1);
    }

    public static void getMatchesWonByAllTeamInAllYear(String matchesLine) {
        String[] match;
        match = matchesLine.split(",");
        String winnerTeam = match[10];

        if(!winnerTeam.equals("") && matchesWonByAllTeamInAllYear.containsKey(winnerTeam)){
            matchesWonByAllTeamInAllYear.put(winnerTeam, matchesWonByAllTeamInAllYear.get(winnerTeam) + 1);
        }
        else if(!winnerTeam.equals(""))
        {
            matchesWonByAllTeamInAllYear.put(winnerTeam, 1);
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
            }
            while((deliveriesLine = deliveriesBufferedReader.readLine()) != null)
            {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(matchesPerYear);
        System.out.println();
        System.out.println(matchesWonByAllTeamInAllYear);
    }
}
