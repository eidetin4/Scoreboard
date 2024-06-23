package scoreboard;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class ScoreboardTest {

    private static final String MEXICO = "Mexico";
    private static final String CANADA = "Canada";
    private static final String SPAIN = "Spain";
    private static final String BRAZIL = "Brazil";
    private static final String GERMANY = "Germany";
    private static final String FRANCE = "France";
    private static final String URUGUAY = "Uruguay";
    private static final String ITALY = "Italy";
    private static final String ARGENTINA = "Argentina";
    private static final String AUSTRALIA = "Australia";

    private Scoreboard scoreboard;

    @Before
    public void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    public void testGetEmptyScoreboard() {
        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals("Initial scoreboard should have size 0", 0, matchSummary.size());
        assertTrue("Initial scoreboard should be empty", matchSummary.isEmpty());
    }

    @Test
    public void testStartOneMatch() {
        int matchId = scoreboard.startMatch(MEXICO, CANADA);
        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals("Match ID should be 1", 1, matchId);
        assertEquals("Scoreboard should have one match", 1, matchSummary.size());
        assertMatch(matchSummary.getFirst(), MEXICO, CANADA, 0, 0);
    }

    @Test
    public void testStartMultipleMatches() {
        int matchId1 = scoreboard.startMatch(MEXICO, CANADA);
        int matchId2 = scoreboard.startMatch(SPAIN, BRAZIL);
        int matchId3 = scoreboard.startMatch(GERMANY, FRANCE);
        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals("First match ID should be 1", 1, matchId1);
        assertEquals("Second match ID should be 2", 2, matchId2);
        assertEquals("Third match ID should be 3", 3, matchId3);
        assertEquals("Scoreboard should have size 3", 3, matchSummary.size());
        assertMatch(matchSummary.get(0), GERMANY, FRANCE, 0, 0);
        assertMatch(matchSummary.get(1), SPAIN, BRAZIL, 0, 0);
        assertMatch(matchSummary.get(2), MEXICO, CANADA, 0, 0);
    }

    @Test
    public void testStartMatchWithInvalidTeamNames() {
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("", ""));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(MEXICO, ""));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("", CANADA));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(null, null));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(MEXICO, null));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(null, CANADA));
        assertEquals("Scoreboard should remain empty", 0, scoreboard.getMatchSummary().size());
    }

    @Test
    public void testUpdateScoreOneMatch() {
        int matchId = scoreboard.startMatch(MEXICO, CANADA);

        scoreboard.updateScore(matchId, 0, 5);

        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals("Scoreboard should have one match", 1, matchSummary.size());
        assertMatch(matchSummary.getFirst(), MEXICO, CANADA, 0, 5);
    }

    @Test
    public void testUpdateScoreMultipleMatches() {
        int matchId1 = scoreboard.startMatch(MEXICO, CANADA);
        int matchId2 = scoreboard.startMatch(SPAIN, BRAZIL);
        int matchId3 = scoreboard.startMatch(GERMANY, FRANCE);

        scoreboard.updateScore(matchId1, 0, 5);
        scoreboard.updateScore(matchId2, 10, 2);
        scoreboard.updateScore(matchId3, 2, 2);

        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals("Scoreboard should have three matches", 3, matchSummary.size());
        assertMatch(matchSummary.get(0), SPAIN, BRAZIL, 10, 2);
        assertMatch(matchSummary.get(1), MEXICO, CANADA, 0, 5);
        assertMatch(matchSummary.get(2), GERMANY, FRANCE, 2, 2);
    }

    @Test
    public void testUpdateScoreMatchNotFound() {
        assertThrows("Should throw exception for non-existent match", IllegalArgumentException.class, () -> scoreboard.updateScore(1000, 0, 5));
    }

    @Test
    public void testUpdateScoreMatchFinished() {
        int matchId = scoreboard.startMatch(MEXICO, CANADA);

        scoreboard.finishMatch(matchId);

        assertThrows("Should throw exception for finished match", IllegalArgumentException.class, () -> scoreboard.updateScore(matchId, 0, 5));

    }

    @Test
    public void testUpdateScoreWithNegativeScores() {
        int matchId = scoreboard.startMatch(MEXICO, CANADA);

        assertThrows("Should throw exception for negative score", IllegalArgumentException.class, () -> scoreboard.updateScore(matchId, -1, 5));
    }

    @Test
    public void testFinishOneMatch() {
        int matchId1 = scoreboard.startMatch(MEXICO, CANADA);
        scoreboard.startMatch(SPAIN, BRAZIL);

        scoreboard.finishMatch(matchId1);

        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals("Scoreboard should have one match after finishing one of two", 1, matchSummary.size());
        assertMatch(matchSummary.getFirst(), SPAIN, BRAZIL, 0, 0);
    }

    @Test
    public void testFinishMultipleMatches() {
        int matchId1 = scoreboard.startMatch(MEXICO, CANADA);
        scoreboard.startMatch(SPAIN, BRAZIL);
        int matchId3 = scoreboard.startMatch(GERMANY, FRANCE);

        scoreboard.finishMatch(matchId1);
        scoreboard.finishMatch(matchId3);

        List<Match> matchSummary = scoreboard.getMatchSummary();

        assertEquals("Scoreboard should have one match after finishing two of three", 1, matchSummary.size());
        assertMatch(matchSummary.getFirst(), SPAIN, BRAZIL, 0, 0);
    }

    @Test
    public void testFinishMatchNotFound() {
        assertThrows("Should throw exception for non-existent match", IllegalArgumentException.class, () -> scoreboard.finishMatch(1));
    }

    @Test
    public void testFinishMatchAlreadyFinished() {
        int matchId = scoreboard.startMatch(MEXICO, CANADA);

        scoreboard.finishMatch(matchId);

        assertThrows("Should throw exception for already finished match", IllegalArgumentException.class, () -> scoreboard.finishMatch(matchId));
    }

    @Test
    public void testGetInitialMatchSummary() {
        scoreboard.startMatch(MEXICO, CANADA);
        scoreboard.startMatch(SPAIN, BRAZIL);
        scoreboard.startMatch(GERMANY, FRANCE);
        scoreboard.startMatch(URUGUAY, ITALY);
        scoreboard.startMatch(ARGENTINA, AUSTRALIA);

        List<Match> initialMatchSummary = scoreboard.getMatchSummary();

        assertEquals("Scoreboard should have five matches", 5, initialMatchSummary.size());
        assertMatch(initialMatchSummary.get(0), ARGENTINA, AUSTRALIA, 0, 0);
        assertMatch(initialMatchSummary.get(1), URUGUAY, ITALY, 0, 0);
        assertMatch(initialMatchSummary.get(2), GERMANY, FRANCE, 0, 0);
        assertMatch(initialMatchSummary.get(3), SPAIN, BRAZIL, 0, 0);
        assertMatch(initialMatchSummary.get(4), MEXICO, CANADA, 0, 0);
    }

    @Test
    public void testGetUpdatedMatchSummary() {
        int matchId1 = scoreboard.startMatch(MEXICO, CANADA);
        int matchId2 = scoreboard.startMatch(SPAIN, BRAZIL);
        int matchId3 = scoreboard.startMatch(GERMANY, FRANCE);
        int matchId4 = scoreboard.startMatch(URUGUAY, ITALY);
        int matchId5 = scoreboard.startMatch(ARGENTINA, AUSTRALIA);

        scoreboard.updateScore(matchId1, 0, 5);
        scoreboard.updateScore(matchId2, 10, 2);
        scoreboard.updateScore(matchId3, 2, 2);
        scoreboard.updateScore(matchId4, 6, 6);
        scoreboard.updateScore(matchId5, 3, 1);

        List<Match> updatedMatchSummary = scoreboard.getMatchSummary();

        assertEquals("Scoreboard should have five matches", 5, updatedMatchSummary.size());
        assertMatch(updatedMatchSummary.get(0), URUGUAY, ITALY, 6, 6);
        assertMatch(updatedMatchSummary.get(1), SPAIN, BRAZIL, 10, 2);
        assertMatch(updatedMatchSummary.get(2), MEXICO, CANADA, 0, 5);
        assertMatch(updatedMatchSummary.get(3), ARGENTINA, AUSTRALIA, 3, 1);
        assertMatch(updatedMatchSummary.get(4), GERMANY, FRANCE, 2, 2);
    }

    @Test
    public void testMatchSummaryImmutability() {
        int matchId = scoreboard.startMatch(MEXICO, CANADA);

        scoreboard.updateScore(matchId, 0, 5);

        List<Match> matchSummary = scoreboard.getMatchSummary();
        Match match = matchSummary.getFirst();

        match.setScore(5, 1);
        match.finishMatch();

        List<Match> maybeChangedMatchSummary = scoreboard.getMatchSummary();
        Match maybeChangedMatch = maybeChangedMatchSummary.getFirst();

        assertEquals("Home score should not change", 0, maybeChangedMatch.getHomeScore());
        assertEquals("Away score should not change", 5, maybeChangedMatch.getAwayScore());
        assertFalse("Match should not be finished", maybeChangedMatch.isFinished());
        assertEquals("Scoreboard should have one match", 1, maybeChangedMatchSummary.size());
    }

    private static void assertMatch(Match match, String homeTeam, String awayTeam, int homeScore, int awayScore) {
        assertEquals("Home team should match", match.getHomeTeam(), homeTeam);
        assertEquals("Away team should match", match.getAwayTeam(), awayTeam);
        assertEquals("Home score should match", match.getHomeScore(), homeScore);
        assertEquals("Away score should match", match.getAwayScore(), awayScore);
    }
}
