package scoreboard;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {

    private final Map<Integer, Match> matches;
    private int nextMatchId;

    public Scoreboard() {
        matches = new HashMap<>();
        nextMatchId = 1;
    }

    public int startMatch(String homeTeam, String awayTeam, long startTime) {
        int matchId = nextMatchId++;

        matches.put(matchId, new Match(matchId, homeTeam, awayTeam, startTime));

        return matchId;
    }
}
