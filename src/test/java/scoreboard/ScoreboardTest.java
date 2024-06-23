package scoreboard;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScoreboardTest {

    @Test
    public void testStartMatch() {
        Scoreboard scoreboard = new Scoreboard();
        assertEquals(0, scoreboard.getMatchSummary().size());

        int matchId1 = scoreboard.startMatch("Mexico", "Canada");
        int matchId2 = scoreboard.startMatch("Spain", "Brazil");

        assertEquals(1, matchId1);
        assertEquals(2, matchId2);

        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals(2, matchSummary.size());
        assertMatch(matchSummary.get(0), "Spain", "Brazil", 0, 0);
        assertMatch(matchSummary.get(1), "Mexico", "Canada", 0, 0);
    }

    @Test
    public void testUpdateScore() {
        Scoreboard scoreboard = new Scoreboard();
    }

    @Test
    public void testFinishMatch() {
        Scoreboard scoreboard = new Scoreboard();
    }

    @Test
    public void testGetMatchSummary() {
        Scoreboard scoreboard = new Scoreboard();
        assertEquals(0, scoreboard.getMatchSummary().size());

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.startMatch("Argentina", "Australia");

        List<Match> initialMatchSummary = scoreboard.getMatchSummary();
        assertEquals(5, initialMatchSummary.size());

        assertMatch(initialMatchSummary.get(0), "Argentina", "Australia", 0, 0);
        assertMatch(initialMatchSummary.get(1), "Uruguay", "Italy", 0, 0);
        assertMatch(initialMatchSummary.get(2), "Germany", "France", 0, 0);
        assertMatch(initialMatchSummary.get(3), "Spain", "Brazil", 0, 0);
        assertMatch(initialMatchSummary.get(4), "Mexico", "Canada", 0, 0);
    }

    private static void assertMatch(Match match, String homeTeam, String awayTeam, int homeScore, int awayScore) {
        assertEquals(match.getHomeTeam(), homeTeam);
        assertEquals(match.getAwayTeam(), awayTeam);
        assertEquals(match.getHomeScore(), homeScore);
        assertEquals(match.getAwayScore(), awayScore);
    }
}
