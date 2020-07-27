package com.ssafy.devlog.dto;

public class PortfolioProject {
	private int seq;
	private int seq_post_portfolio;
	private int seq_post_project;
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
	public int getSeq_post_project() {
		return seq_post_project;
	}
	public void setSeq_post_project(int seq_post_project) {
		this.seq_post_project = seq_post_project;
	}
	@Override
	public String toString() {
		return "PortfolioProject [seq=" + seq + ", seq_post_portfolio=" + seq_post_portfolio + ", seq_post_project="
				+ seq_post_project + "]";
	}
	
}
