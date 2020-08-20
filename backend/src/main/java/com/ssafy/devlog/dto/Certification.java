package com.ssafy.devlog.dto;

public class Certification {
	private int seq;
	private String key;
	private String value;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Certification() {
	}
	public Certification(int seq, String key, String value) {
		this.seq = seq;
		this.key = key;
		this.value = value;
	}
}
