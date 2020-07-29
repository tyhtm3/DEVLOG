package com.ssafy.devlog.dto;

public class PostTag {

	private int seq;
	private int seq_post;
	private String tag;
	
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "PostTag [seq=" + seq + ", seq_post=" + seq_post + ", tag=" + tag + "]";
	}
	
}
