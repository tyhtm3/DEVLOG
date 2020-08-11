package com.ssafy.devlog.dto;

public class PostCommentUser {
	
	private int seq;
	private int seq_post;
	private int seq_user;
	private String content;
	private String regtime;
	private String id;
	private String nickname;
	private String name;
	private String profile_img_url;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfile_img_url() {
		return profile_img_url;
	}
	public void setProfile_img_url(String profile_img_url) {
		this.profile_img_url = profile_img_url;
	}
	
	@Override
	public String toString() {
		return "PostCommentUser [seq=" + seq + ", seq_post=" + seq_post + ", seq_user=" + seq_user + ", content="
				+ content + ", regtime=" + regtime + ", id=" + id + ", nickname=" + nickname + ", name=" + name
				+ ", profile_img_url=" + profile_img_url + "]";
	}

	
}
