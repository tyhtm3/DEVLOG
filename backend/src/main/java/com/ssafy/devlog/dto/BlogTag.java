package com.ssafy.devlog.dto;

public class BlogTag {
	private int seq;
	private int seq_blog;
	private String tag;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getSeq_blog() {
		return seq_blog;
	}
	public void setSeq_blog(int seq_blog) {
		this.seq_blog = seq_blog;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public BlogTag() {
	}
	public BlogTag(int seq, int seq_blog, String tag) {
		super();
		this.seq = seq;
		this.seq_blog = seq_blog;
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "BlogTag [seq=" + seq + ", seq_blog=" + seq_blog + ", tag=" + tag + "]";
	}
	
}
