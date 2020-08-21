package com.ssafy.devlog.dto;

public class PortfolioLanguage {
	private int seq;
	private int seq_post_portfolio;
	private String language;
	private String level;
	
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getSeq_post_portfolio() {
		return seq_post_portfolio;
	}


	public void setSeq_post_portfolio(int seq_post_portfolio) {
		this.seq_post_portfolio = seq_post_portfolio;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public PortfolioLanguage(int seq, int seq_post_portfolio, String language, String level) {
		this.seq = seq;
		this.seq_post_portfolio = seq_post_portfolio;
		this.language = language;
		this.level = level;
	}
	
}
