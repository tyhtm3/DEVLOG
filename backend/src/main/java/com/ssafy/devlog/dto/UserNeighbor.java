package com.ssafy.devlog.dto;

public class UserNeighbor {
	private int seq;
	private int seq_user;
	private int seq_neighbor;
	private String regtime;
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
	public int getSeq_neighbor() {
		return seq_neighbor;
	}
	public void setSeq_neighbor(int seq_neighbor) {
		this.seq_neighbor = seq_neighbor;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public UserNeighbor() {
	}
	public UserNeighbor(int seq, int seq_user, int seq_neighbor, String regtime) {
		this.seq = seq;
		this.seq_user = seq_user;
		this.seq_neighbor = seq_neighbor;
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "UserNeighbor [seq=" + seq + ", seq_user=" + seq_user + ", seq_neighbor=" + seq_neighbor + ", regtime="
				+ regtime + "]";
	}
	
	
}
