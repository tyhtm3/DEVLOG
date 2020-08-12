package com.ssafy.devlog.dto;

public class ProjectRole {
	private int seq;
	private int seq_post_project;
	private String role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "ProjectRole [seq=" + seq + ", seq_post_project=" + seq_post_project + ", role=" + role + "]";
	}
	
}
