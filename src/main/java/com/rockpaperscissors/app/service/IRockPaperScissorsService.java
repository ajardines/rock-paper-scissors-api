package com.rockpaperscissors.app.service;

import java.util.List;

import com.rockpaperscissors.app.model.Match;
import com.rockpaperscissors.app.model.Score;

public interface IRockPaperScissorsService {

	/**
	 * Generate a new match.
	 * 
	 * @param sessionId.
	 * @return New match generated.
	 */
	public Match generateMatch(String sessionId);
	
	/**
	 * Select winner.
	 * 
	 * @param match.
	 * @return Match containing the result.
	 */
	public Match selectWinner(Match match);

	/**
	 * Get the matches by the session id.
	 * 
	 * @param sessionId.
	 * @return List of matches found.
	 */
	public List<Match> getMatchesBySessionId(String sessionId);

	/**
	 * Get the total score.
	 * 
	 * @return score.
	 */
	public Score getScore();

	/**
	 * Restart the game by the session id.
	 * 
	 * @param sessionId.
	 */
	public void resetGame(String sessionId);
}
