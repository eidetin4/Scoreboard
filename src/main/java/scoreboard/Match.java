package scoreboard;

public class Match implements Cloneable {

    private final int matchId;
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;

    Match(int matchId, String homeTeam, String awayTeam) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        homeScore = 0;
        awayScore = 0;
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

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    void setScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    @Override
    public Match clone() {
        try {
            return (Match) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
