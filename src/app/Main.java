package app;

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
        List<Match> matchesData = getMatchData();
    }

    private static List<Match> getMatchData() {
        List<Match> matches = new ArrayList<>();
        String matchesPath = "/home/ravi/Mountblue/IPL_Project/src/app/dataset/matches.csv";
        String matchesLine;

        try {
            BufferedReader matchesBufferedReader = new BufferedReader(new FileReader(matchesPath));
            matchesBufferedReader.readLine();

            while ((matchesLine = matchesBufferedReader.readLine()) != null) {
                Match match = new Match();
                String[] matchData = matchesLine.split(",");
                match.setId(Integer.parseInt(matchData[ID]));
                match.setSeason(Integer.parseInt(matchData[SEASON]));
                match.setTossWinner(matchData[TOSS_WINNER]);
                match.setWinner(matchData[WINNER]);
                match.setPlayerOfMatch(matchData[PLAYER_OF_MATCH]);
                matches.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }
}