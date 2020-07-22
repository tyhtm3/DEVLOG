package com.ssafy.devlog.dto;

public class UserTag {
	private int seq;
	private int seq_user;
	private String tag;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getSeq_user() {
		return seq_user;
	}
	public void setSeq_user(int seq_user) {
		this.seq_user = seq_user;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public UserTag() {
	}
	public UserTag(int seq, int seq_user, String tag) {
		super();
		this.seq = seq;
		this.seq_user = seq_user;
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "BlogTag [seq=" + seq + ", seq_user=" + seq_user + ", tag=" + tag + "]";
	}
	
}
