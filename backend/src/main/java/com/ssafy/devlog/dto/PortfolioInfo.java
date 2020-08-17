package com.ssafy.devlog.dto;

public class PortfolioInfo {
	private int seq;
	private int seq_post_portfolio;
	private String introduction;
	private String address;
	private String objective;
	
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
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
	@Override
	public String toString() {
		return "PortfolioInfo [seq=" + seq + ", seq_post_portfolio=" + seq_post_portfolio + ", introduction="
				+ introduction + ", address=" + address + ", objective=" + objective + "]";
	}
	

	
}
