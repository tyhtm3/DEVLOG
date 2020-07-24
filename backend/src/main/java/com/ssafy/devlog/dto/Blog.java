package com.ssafy.devlog.dto;

public class Blog {
	private int seq;
	private String blog_name;
	private String blog_detail;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getBlog_name() {
		return blog_name;
	}
	public void setBlog_name(String blog_name) {
		this.blog_name = blog_name;
	}
	public String getBlog_detail() {
		return blog_detail;
	}
	public void setBlog_detail(String blog_detail) {
		this.blog_detail = blog_detail;
	}
	public Blog() {
	}
	public Blog(int seq, String blog_name, String blog_detail) {
		this.seq = seq;
		this.blog_name = blog_name;
		this.blog_detail = blog_detail;
	}
	@Override
	public String toString() {
		return "Blog [seq=" + seq + ", blog_name=" + blog_name + ", blog_detail="
				+ blog_detail + "]";
	}
	
	
}
