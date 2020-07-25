package com.ssafy.devlog.dto;

public class PostLike {
	
	private int seq;
	private int seq_post;
	private int seq_user;
	
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
	@Override
	public String toString() {
		return "PostLike [seq=" + seq + ", seq_post=" + seq_post + ", seq_user=" + seq_user + "]";
	}
	
}
