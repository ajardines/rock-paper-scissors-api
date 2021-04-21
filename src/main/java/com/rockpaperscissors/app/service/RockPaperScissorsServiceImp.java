package com.rockpaperscissors.app.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.rockpaperscissors.app.enumeration.RockPaperScissorsEnum;
import com.rockpaperscissors.app.model.Match;
import com.rockpaperscissors.app.model.Score;
import com.rockpaperscissors.app.repository.RockPaperScissorsRepository;

@Service
public class RockPaperScissorsServiceImp implements IRockPaperScissorsService {

	@Override
	public Match generateMatch(String sessionId) {

		Match match = new Match(sessionId);
		Random rnd = new Random();
		int randomValue = rnd.nextInt(3);
		match.setPlayer2(RockPaperScissorsEnum.values()[randomValue]);
		
		return selectWinner(match);
	}
	
	@Override
	public Match selectWinner(Match match) {
		if (match.getPlayer1().equals(match.getPlayer2())) {
			match.setDraw(true);
		} else if (match.getPlayer2().equals(RockPaperScissorsEnum.Paper)) {
			match.setPlayer2Wins(true);
		} else {
			match.setPlayer1Wins(true);
		}

		RockPaperScissorsRepository.addMatch(match);

		return match;
	}

	@Override
	public Score getScore() {

		List<Match> matches = RockPaperScissorsRepository.getAllMatches();
		Score score = new Score();
		score.setTotalRounds(matches.size());
		score.setTotalWinsPlayer1((int) matches.stream().filter(match -> match.getPlayer1Wins()).count());
		score.setTotalWinsPlayer2((int) matches.stream().filter(match -> match.getPlayer2Wins()).count());
		score.setTotalDraws((int) matches.stream().filter(match -> match.getDraw()).count());

		return score;
	}

	@Override
	public List<Match> getMatchesBySessionId(String sessionId) {
		return RockPaperScissorsRepository.getMatchesBySessionId(sessionId);
	}

	@Override
	public void resetGame(String sessionId) {
		RockPaperScissorsRepository.resetGame(sessionId);
		return;
	}

}
