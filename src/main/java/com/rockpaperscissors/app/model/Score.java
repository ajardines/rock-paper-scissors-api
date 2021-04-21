package com.rockpaperscissors.app.model;

import java.io.Serializable;

public class Score implements Serializable {

	private static final long serialVersionUID = -2986359050461511713L;

	private Integer totalRounds;
	private Integer totalWinsPlayer1;
	private Integer totalWinsPlayer2;
	private Integer totalDraws;

	public Integer getTotalRounds() {
		return totalRounds;
	}

	public void setTotalRounds(Integer totalRounds) {
		this.totalRounds = totalRounds;
	}

	public Integer getTotalWinsPlayer1() {
		return totalWinsPlayer1;
	}

	public void setTotalWinsPlayer1(Integer totalWinsPlayer1) {
		this.totalWinsPlayer1 = totalWinsPlayer1;
	}

	public Integer getTotalWinsPlayer2() {
		return totalWinsPlayer2;
	}

	public void setTotalWinsPlayer2(Integer totalWinsPlayer2) {
		this.totalWinsPlayer2 = totalWinsPlayer2;
	}

	public Integer getTotalDraws() {
		return totalDraws;
	}

	public void setTotalDraws(Integer totalDraws) {
		this.totalDraws = totalDraws;
	}

}
