package com.ssafy.devlog.dto;

public class ProjectStack {
	private int seq;
	private int seq_post_project;
	private String stack;
	private String stack_img_url;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getSeq_post_project() {
		return seq_post_project;
	}

	public void setSeq_post_project(int seq_post_project) {
		this.seq_post_project = seq_post_project;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public String getStack_img_url() {
		return stack_img_url;
	}

	public void setStack_img_url(String stack_img_url) {
		this.stack_img_url = stack_img_url;
	}

}
