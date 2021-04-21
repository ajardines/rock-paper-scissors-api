package com.rockpaperscissors.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rockpaperscissors.app.enumeration.RockPaperScissorsEnum;
import com.rockpaperscissors.app.model.Match;
import com.rockpaperscissors.app.model.Score;
import com.rockpaperscissors.app.repository.RockPaperScissorsRepository;
import com.rockpaperscissors.app.service.RockPaperScissorsServiceImp;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RockPaperScissorsServiceImp.class)
class RockPaperScissorsServiceTests {

	@InjectMocks
	RockPaperScissorsServiceImp rockPaperScissorsService;

	static MockedStatic<RockPaperScissorsRepository> mockRockPaperScissorsRepository;

	@BeforeAll
	public static void init() {
		mockRockPaperScissorsRepository = Mockito.mockStatic(RockPaperScissorsRepository.class);
	}

	@AfterAll
	public static void close() {
		mockRockPaperScissorsRepository.close();
	}

	@Test
	void getScoreTest() throws Exception {

		Match match1 = new Match("1");
		match1.setPlayer1Wins(true);
		Match match2 = new Match("2");
		match2.setPlayer2Wins(true);

		mockRockPaperScissorsRepository.when(() -> RockPaperScissorsRepository.getAllMatches())
				.thenReturn(Arrays.asList(match1, match2));

		Score score = rockPaperScissorsService.getScore();

		assertEquals(2, score.getTotalRounds());
		assertEquals(0, score.getTotalDraws());
		assertEquals(1, score.getTotalWinsPlayer1());
		assertEquals(1, score.getTotalWinsPlayer2());
	}

	@Test
	void getMatchesBySessionIdTest() throws Exception {

		Match expectedMatch = new Match("1");
		expectedMatch.setPlayer1Wins(true);

		mockRockPaperScissorsRepository.when(() -> RockPaperScissorsRepository.getMatchesBySessionId("1"))
				.thenReturn(Arrays.asList(expectedMatch));

		List<Match> matches = rockPaperScissorsService.getMatchesBySessionId("1");

		assertEquals(Arrays.asList(expectedMatch), matches);
	}

	@Test
	void generateMatchTest() throws Exception {

		Match match = rockPaperScissorsService.generateMatch("1");
		assertEquals(true, match.getActivePlay());
		assertEquals("1", match.getSessionId());
		assertNotNull(match);
	}
	
	@Test
	void selectWinner() throws Exception {

		Match matchResult = new Match();
		Match match = new Match("1");
		match.setPlayer2(RockPaperScissorsEnum.Rock);
		matchResult = rockPaperScissorsService.selectWinner(match);
		assertEquals(true, matchResult.getDraw());
		assertEquals(false, matchResult.getPlayer1Wins());
		assertEquals(false, matchResult.getPlayer2Wins());
		
		match = new Match("1");
		match.setPlayer2(RockPaperScissorsEnum.Paper);
		matchResult = rockPaperScissorsService.selectWinner(match);
		assertEquals(false, matchResult.getDraw());
		assertEquals(false, matchResult.getPlayer1Wins());
		assertEquals(true, matchResult.getPlayer2Wins());
		
		match = new Match("1");
		match.setPlayer2(RockPaperScissorsEnum.Scissors);
		matchResult = rockPaperScissorsService.selectWinner(match);
		assertEquals(false, matchResult.getDraw());
		assertEquals(true, matchResult.getPlayer1Wins());
		assertEquals(false, matchResult.getPlayer2Wins());
	}

}
