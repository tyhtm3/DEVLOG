package com.ssafy.devlog.dto;

public class Project {
	private int seq;
	private int seq_blog;
	private String title;
	private String content;
	private String regtime;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
		return "Post [seq=" + seq + ", seq_blog=" + seq_blog + ", title=" + title + ", content=" + content
				+ ", regtime=" + regtime + "]";
	}
	
}
