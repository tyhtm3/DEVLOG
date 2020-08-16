package com.ssafy.devlog.dto;

public class PortfolioSocial {
	private int seq;
	private int seq_post_portfolio;
	private String link;
	private String icon;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public PortfolioSocial(int seq, int seq_post_portfolio, String link, String icon) {
		this.seq = seq;
		this.seq_post_portfolio = seq_post_portfolio;
		this.link = link;
		this.icon = icon;
	}
	
}
