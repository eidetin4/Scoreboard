package scoreboard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreboardTest {

    @Test
    public void testStartMatch() {
        Scoreboard scoreboard = new Scoreboard();

        int matchId1 = scoreboard.startMatch("Mexico", "Canada", System.currentTimeMillis());
        int matchId2 = scoreboard.startMatch("Spain", "Brazil", System.currentTimeMillis() + 1);

        assertEquals(1, matchId1);
        assertEquals(2, matchId2);
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
    }
}
