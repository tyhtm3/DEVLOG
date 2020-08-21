package com.ssafy.devlog.dto;

public class PortfolioCertification {
	private int seq;
	private int seq_post_portfolio;
	private String certification;
	private String date;
	
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
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public PortfolioCertification(int seq, int seq_post_portfolio, String certification, String date) {
		this.seq = seq;
		this.seq_post_portfolio = seq_post_portfolio;
		this.certification = certification;
		this.date = date;
	}
	
}
