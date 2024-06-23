package scoreboard;

public class Match {

    private final int matchId;
    private final String homeTeam;
    private final String awayTeam;
    private final long startTime;
    private int homeScore;
    private int awayScore;
    private boolean isFinished;

    Match(int matchId, String homeTeam, String awayTeam, long startTime) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTime = startTime;
        homeScore = 0;
        awayScore = 0;
        isFinished = false;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public void setMatchFinished() {
        isFinished = true;
    }
}
