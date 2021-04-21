package com.rockpaperscissors.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rockpaperscissors.app.controller.RockPaperScissorsController;
import com.rockpaperscissors.app.model.Match;
import com.rockpaperscissors.app.model.Score;
import com.rockpaperscissors.app.service.IRockPaperScissorsService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RockPaperScissorsController.class)
class RockPaperScissorsControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IRockPaperScissorsService rockPaperScissorsService;

	@Test
	void newMatchTest() throws Exception {

		MockHttpSession session = new MockHttpSession();

		Match match = new Match(session.getId());
		when(rockPaperScissorsService.generateMatch(session.getId())).thenReturn(match);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/new-match").accept(MediaType.APPLICATION_JSON)
				.session(session);

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String responseJson = objectWriter.writeValueAsString(match);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
		JSONAssert.assertEquals(responseJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	void getMatchesBySessionIdTest() throws Exception {

		MockHttpSession session = new MockHttpSession();

		Match match = new Match(session.getId());
		when(rockPaperScissorsService.getMatchesBySessionId(session.getId())).thenReturn(Arrays.asList(match));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/matches-by-session")
				.accept(MediaType.APPLICATION_JSON).session(session);

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String responseJson = objectWriter.writeValueAsString(Arrays.asList(match));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		JSONAssert.assertEquals(responseJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	void getScoreTest() throws Exception {

		Score score = new Score();
		score.setTotalRounds(10);
		score.setTotalDraws(1);
		score.setTotalWinsPlayer1(3);
		score.setTotalWinsPlayer2(3);

		when(rockPaperScissorsService.getScore()).thenReturn(score);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/score").accept(MediaType.APPLICATION_JSON);

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String responseJson = objectWriter.writeValueAsString(score);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		JSONAssert.assertEquals(responseJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	void resetGameTest() throws Exception {

		MockHttpSession session = new MockHttpSession();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/reset").accept(MediaType.APPLICATION_JSON)
				.session(session);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
	}

}
