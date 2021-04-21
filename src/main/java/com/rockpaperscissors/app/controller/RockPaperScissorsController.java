package com.rockpaperscissors.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rockpaperscissors.app.model.Match;
import com.rockpaperscissors.app.model.Score;
import com.rockpaperscissors.app.service.IRockPaperScissorsService;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
public class RockPaperScissorsController {

	@Autowired
	IRockPaperScissorsService rockPaperScissorsService;

	/**
	 * Generate a new match.
	 * 
	 * @param session Session contains the identifier of the user.
	 * @return The new match created.
	 */
	@PostMapping("/new-match")
	public ResponseEntity<Match> newMatch(HttpSession session) {
		Match newGame = rockPaperScissorsService.generateMatch(session.getId());
		return new ResponseEntity<Match>(newGame, HttpStatus.CREATED);
	}

	/**
	 * Get the matches by the session id.
	 * 
	 * @param session Session contains the identifier of the user.
	 * @return List of matches found.
	 */
	@GetMapping("/matches-by-session")
	public ResponseEntity<List<Match>> getMatchesBySessionId(HttpSession session) {
		List<Match> matches = rockPaperScissorsService.getMatchesBySessionId(session.getId());
		return new ResponseEntity<List<Match>>(matches, HttpStatus.OK);
	}

	/**
	 * Get the total score.
	 * 
	 * @return score.
	 */
	@GetMapping("/score")
	public ResponseEntity<Score> getScore() {
		Score score = rockPaperScissorsService.getScore();
		return new ResponseEntity<Score>(score, HttpStatus.OK);
	}

	/**
	 * Restart the game by the session id.
	 * 
	 * @param session Session contains the identifier of the user.
	 * @return Void.
	 */
	@PutMapping("/reset")
	public ResponseEntity<Void> resetGame(HttpSession session) {
		rockPaperScissorsService.resetGame(session.getId());
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
