package com.ssafy.devlog.dto;

public class PortfolioEducation {
	private int seq;
	private int seq_post_portfolio;
	private String major;
	private String name;
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
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public PortfolioEducation(int seq, int seq_post_portfolio, String major, String name, String start, String end) {
		this.seq = seq;
		this.seq_post_portfolio = seq_post_portfolio;
		this.major = major;
		this.name = name;
		this.start = start;
		this.end = end;
	}
	
	
}
