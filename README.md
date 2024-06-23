# Live football world cup scoreboard

This is a simple Java library for managing a live football world cup scoreboard.

It supports starting new matches, updating scores, finishing matches, and getting a summary of the matches in progress.

## Features

- Start a new match
- Update the score of a match
- Finish a match
- Get a summary of the matches in progress

## Usage

```java
Scoreboard scoreboard = new Scoreboard();
int matchId = scoreboard.startMatch("Mexico", "Canada");
int homeScore = 0;
int awayScore = 5;

scoreboard.updateScore(matchId, homeScore, awayScore);
scoreboard.finishMatch(matchId);
List<Match> summary = scoreboard.getSummary();
```

## Notes
- Since the matchId is incremented by 1 each time a new match is started, it is possible to use the matchId in the match summary sort instead of start time. Normally start time would be better, but this time we keep it simple.
- The finished matches are not removed from the match summary Map, only flagged as finished. This is to keep the matches, for possible future handling.
- The matchId is not reset, so it will eventually overflow. This is not handled in this version to avoid over engineering and keep it simple.
- Mutable Match should not be exposed to the outside world, for simplicity it is only handled by cloning and returning a copy of the match list this time.