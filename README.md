# Live football world cup scoreboard

This is a simple Java library for managing a live football world cup scoreboard.

It supports starting new matches, updating scores, finishing matches, and getting a summary of the matches in progress.

It uses Maven for dependency management and building, and JUnit for testing.

## Features

The scoreboard supports the following operations:
1. **Start a new match**: Adds a new match to the scoreboard with an initial score of 0-0.
    - Parameters: Home team, Away team
2. **Update score**: Updates the score of an ongoing match.
    - Parameters: Match id, Home team score, Away team score
3. **Finish match**: Removes a match from the scoreboard.
    - Parameters: Match id
4. **Get summary**: Provides a summary of ongoing matches ordered by their total score. Matches with the same total score are ordered by the most recently started match.

## Usage

```java
Scoreboard scoreboard = new Scoreboard(); // Create a new scoreboard
int matchId = scoreboard.startMatch("Mexico", "Canada"); // Start a new match

int homeScore = 0;
int awayScore = 5;
scoreboard.updateScore(matchId, homeScore, awayScore); // Update the score of a match

scoreboard.finishMatch(matchId); // Finish a match

List<Match> summary = scoreboard.getSummary(); // Get a summary of the ongoing matches
```

## Notes
- Since the matchId is incremented by 1 each time a new match is started, it is possible to use the matchId in the match summary sort instead of start time. For additional handling start time is useful, but this time we keep it simple.
- The matchId is not reset, so it will eventually overflow. This is not handled in this version to keep it simple.
- Mutable Match should not be exposed to the outside world, for simplicity this time it is handled by cloning and returning a copy of the match list.
- The exercise does not specify if the scores can decrease or not, but correcting an error can be useful, so it is allowed here.