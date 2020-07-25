package com.ssafy.devlog.dto;

public class PostComment {
	
	private int seq;
	private int seq_post;
	private int seq_user;
	private String content;
	private String regtime;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getSeq_post() {
		return seq_post;
	}
	public void setSeq_post(int seq_post) {
		this.seq_post = seq_post;
	}
	public int getSeq_user() {
		return seq_user;
	}
	public void setSeq_user(int seq_user) {
		this.seq_user = seq_user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "PostComment [seq=" + seq + ", seq_post=" + seq_post + ", seq_user=" + seq_user + ", content=" + content
				+ ", regtime=" + regtime + "]";
	}
	

	
}
