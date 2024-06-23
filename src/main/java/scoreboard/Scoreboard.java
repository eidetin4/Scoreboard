package scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Scoreboard {

    private final Map<Integer, Match> matches;
    private final AtomicInteger nextMatchId;

    public Scoreboard() {
        matches = new HashMap<>();
        nextMatchId = new AtomicInteger(1);
    }

    public int startMatch(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Teams must not be null");
        }

        int matchId = nextMatchId.getAndIncrement();

        matches.put(matchId, new Match(matchId, homeTeam, awayTeam));

        return matchId;
    }

    public void updateScore(int matchId, int homeScore, int awayScore) {

    }

    public void finishMatch(int matchId) {

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
