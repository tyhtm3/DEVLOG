package com.ssafy.devlog.dto;

public class PortfolioInfo {
	private int seq;
	private int seq_post_portfolio;
	private String introduction;
	private String address;
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
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public PortfolioInfo() {
		
	}
	
	public PortfolioInfo(int seq, int seq_post_portfolio, String introduction, String address) {
		this.seq = seq;
		this.seq_post_portfolio = seq_post_portfolio;
		this.introduction = introduction;
		this.address = address;
	}
	
	
}
