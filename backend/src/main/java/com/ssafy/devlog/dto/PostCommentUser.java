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
	private String title;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public PostCommentUser() {
		
	}

	public PostCommentUser(PostComment postComment, User user) {
		this.seq = postComment.getSeq();
		this.seq_post = postComment.getSeq_post();
		this.seq_user = postComment.getSeq_user();
		this.content = postComment.getContent();
		this.regtime = postComment.getRegtime();
		this.id = user.getId();
		this.nickname = user.getNickname();
		this.name = user.getName();
		this.profile_img_url = user.getProfile_img_url();
	}
	
	public PostCommentUser(int seq, int seq_post, int seq_user, String content, String regtime, String id,
			String nickname, String name, String profile_img_url, String title) {
		this.seq = seq;
		this.seq_post = seq_post;
		this.seq_user = seq_user;
		this.content = content;
		this.regtime = regtime;
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.profile_img_url = profile_img_url;
		this.title = title;
	}
	
	
}
