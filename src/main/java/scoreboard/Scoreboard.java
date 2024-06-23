package scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scoreboard {

    private final Map<Integer, Match> matches;
    private int nextMatchId;

    public Scoreboard() {
        matches = new HashMap<>();
        nextMatchId = 1;
    }

    public int startMatch(String homeTeam, String awayTeam) {
        int matchId = nextMatchId++;

        matches.put(matchId, new Match(matchId, homeTeam, awayTeam));

        return matchId;
    }

    public List<Match> getMatchSummary() {
        List<Match> scoreboardMatches = new ArrayList<>(matches.values());

        scoreboardMatches.removeIf(Match::isFinished);

        scoreboardMatches.sort((m1, m2) -> {
            int totalScore1 = m1.getHomeScore() + m1.getAwayScore();
            int totalScore2 = m2.getHomeScore() + m2.getAwayScore();

            if (totalScore1 == totalScore2) {
                return m2.getMatchId() - m1.getMatchId();
            }

            return totalScore2 - totalScore1;
        });

        return scoreboardMatches;
    }
}
