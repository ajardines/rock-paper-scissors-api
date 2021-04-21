package com.rockpaperscissors.app.model;

import java.io.Serializable;

import com.rockpaperscissors.app.enumeration.RockPaperScissorsEnum;

public class Match implements Serializable {

	private static final long serialVersionUID = 3421174732867318898L;

	private String sessionId;
	private Boolean player1Wins;
	private Boolean player2Wins;
	private RockPaperScissorsEnum player1;
	private RockPaperScissorsEnum player2;
	private Boolean draw;
	private Boolean activePlay;

	public Match() {
	}

	public Match(String sessionId) {
		this.sessionId = sessionId;
		this.player1Wins = false;
		this.player2Wins = false;
		this.draw = false;
		this.activePlay = true;
		this.player1 = RockPaperScissorsEnum.Rock;
		this.player2 = null;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Boolean getPlayer1Wins() {
		return player1Wins;
	}

	public void setPlayer1Wins(Boolean player1Wins) {
		this.player1Wins = player1Wins;
	}

	public Boolean getPlayer2Wins() {
		return player2Wins;
	}

	public void setPlayer2Wins(Boolean player2Wins) {
		this.player2Wins = player2Wins;
	}

	public Boolean getDraw() {
		return draw;
	}

	public void setDraw(Boolean draw) {
		this.draw = draw;
	}

	public Boolean getActivePlay() {
		return activePlay;
	}

	public void setActivePlay(Boolean activePlay) {
		this.activePlay = activePlay;
	}

	public RockPaperScissorsEnum getPlayer1() {
		return player1;
	}

	public void setPlayer1(RockPaperScissorsEnum player1) {
		this.player1 = player1;
	}

	public RockPaperScissorsEnum getPlayer2() {
		return player2;
	}

	public void setPlayer2(RockPaperScissorsEnum player2) {
		this.player2 = player2;
	}

}
