package scoreboard;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Scoreboard {

    private final Map<Integer, Match> matches;
    private final AtomicInteger nextMatchId;

    public Scoreboard() {
        matches = new HashMap<>();
        nextMatchId = new AtomicInteger(1); // Possible overflow should be hanlded
    }

    public int startMatch(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Teams name(s) must not be null or empty");
        }

        if (matches.values().stream().anyMatch(m ->
                        m.getHomeTeam().equals(homeTeam) ||
                        m.getAwayTeam().equals(homeTeam) ||
                        m.getHomeTeam().equals(awayTeam) ||
                        m.getAwayTeam().equals(awayTeam))) {
            throw new IllegalArgumentException("Team is already playing");
        }

        int matchId = nextMatchId.getAndIncrement();

        matches.put(matchId, new Match(matchId, homeTeam, awayTeam));

        return matchId;
    }

    public void updateScore(int matchId, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores must not be negative");
        }

        Match match = matches.get(matchId);

        if (match == null) {
            throw new IllegalArgumentException("Match not found");
        }

        match.setScore(homeScore, awayScore);
    }

    public void finishMatch(int matchId) {
        Match match = matches.get(matchId);

        if (match == null) {
            throw new IllegalArgumentException("Match not found");
        }

        matches.remove(matchId);
    }

    public List<Match> getMatchSummary() {
        List<Match> scoreboardMatches = new ArrayList<>(matches.values());

        if (scoreboardMatches.isEmpty()) {
            return List.of();
        }

        if (scoreboardMatches.size() == 1) {
            return List.of(scoreboardMatches.getFirst().clone());
        }

        scoreboardMatches.sort((m1, m2) -> {
            int totalScore1 = m1.getHomeScore() + m1.getAwayScore();
            int totalScore2 = m2.getHomeScore() + m2.getAwayScore();

            if (totalScore1 == totalScore2) {
                return m2.getMatchId() - m1.getMatchId();
            }

            return totalScore2 - totalScore1;
        });

        return scoreboardMatches.stream().map(Match::clone).toList();
    }
}
