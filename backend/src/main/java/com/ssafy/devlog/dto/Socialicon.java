package com.ssafy.devlog.dto;

public class Socialicon {
	private int seq;
	private String name;
	private String icon;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Socialicon(int seq, String name, String icon) {
		super();
		this.seq = seq;
		this.name = name;
		this.icon = icon;
	}
	
}
