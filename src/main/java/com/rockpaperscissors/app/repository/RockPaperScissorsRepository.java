package com.rockpaperscissors.app.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.rockpaperscissors.app.model.Match;

public class RockPaperScissorsRepository {

	public static List<Match> matchHistory = new ArrayList<Match>();

	/**
	 * Add a new object match to the matchHistory list.
	 * 
	 * @param match New Match.
	 * @return Match list
	 */
	public static List<Match> addMatch(Match match) {
		matchHistory.add(match);
		return matchHistory;
	}

	/**
	 * Get all matches.
	 * 
	 * @return Match list.
	 */
	public static List<Match> getAllMatches() {
		return matchHistory;
	}

	/**
	 * Get the matches by the session id.
	 * 
	 * @param sessionId.
	 * @return List of matches found.
	 */
	public static List<Match> getMatchesBySessionId(String sessionId) {
		List<Match> result = matchHistory.stream()
				.filter(match -> match.getSessionId().equals(sessionId) && match.getActivePlay())
				.collect(Collectors.toList());
		Collections.reverse(result);
		return result;
	}

	/**
	 * Restart the game by the session id.
	 * 
	 * @param sessionId.
	 */
	public static void resetGame(String sessionId) {
		matchHistory.stream().filter(match -> match.getSessionId().equals(sessionId) && match.getActivePlay())
				.forEach(match -> match.setActivePlay(false));

	}
}
