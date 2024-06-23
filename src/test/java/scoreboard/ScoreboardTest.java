package scoreboard;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ScoreboardTest {

    @Test
    public void testGetEmptyScoreboard() {
        Scoreboard scoreboard = new Scoreboard();

        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals(0, matchSummary.size());
        assertTrue(matchSummary.isEmpty());
    }

    @Test
    public void testStartOneMatch() {
        Scoreboard scoreboard = new Scoreboard();

        int matchId = scoreboard.startMatch("Mexico", "Canada");

        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals(1, matchId);
        assertEquals(1, matchSummary.size());
        assertMatch(matchSummary.getFirst(), "Mexico", "Canada", 0, 0);
    }

    @Test
    public void testStartMultipleMatches() {
        Scoreboard scoreboard = new Scoreboard();

        int matchId1 = scoreboard.startMatch("Mexico", "Canada");
        int matchId2 = scoreboard.startMatch("Spain", "Brazil");
        int matchId3 = scoreboard.startMatch("Germany", "France");

        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals(1, matchId1);
        assertEquals(2, matchId2);
        assertEquals(3, matchId3);
        assertEquals(3, matchSummary.size());
        assertMatch(matchSummary.get(0), "Germany", "France", 0, 0);
        assertMatch(matchSummary.get(1), "Spain", "Brazil", 0, 0);
        assertMatch(matchSummary.get(2), "Mexico", "Canada", 0, 0);
    }

    @Test
    public void testStartMatchWithEmptyStringTeamNames() {
        Scoreboard scoreboard = new Scoreboard();

        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("", ""));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", ""));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("", "Canada"));
        assertEquals(0, scoreboard.getMatchSummary().size());
    }

    @Test
    public void testStartMatchWithNullTeamNames() {
        Scoreboard scoreboard = new Scoreboard();

        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(null, null));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", null));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(null, "Canada"));
        assertEquals(0, scoreboard.getMatchSummary().size());
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
