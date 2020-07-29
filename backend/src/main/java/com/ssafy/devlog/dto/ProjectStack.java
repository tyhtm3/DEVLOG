package com.ssafy.devlog.dto;

public class ProjectStack {
	private int seq;
	private int seq_post_project;
	private String stack;
	
	
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


	@Override
	public String toString() {
		return "ProjectStack [seq=" + seq + ", seq_post_project=" + seq_post_project + ", stack=" + stack + "]";
	}
	
}
