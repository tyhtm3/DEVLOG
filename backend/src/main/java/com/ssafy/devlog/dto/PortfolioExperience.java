package com.ssafy.devlog.dto;

public class PortfolioExperience {
	private int seq;
	private int seq_post_portfolio;
	private String position;
	private String company;
	private String start;
	private String end;

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public PortfolioExperience(int seq, int seq_post_portfolio, String position, String company, String start,
			String end) {
		this.seq = seq;
		this.seq_post_portfolio = seq_post_portfolio;
		this.position = position;
		this.company = company;
		this.start = start;
		this.end = end;
	}

}
