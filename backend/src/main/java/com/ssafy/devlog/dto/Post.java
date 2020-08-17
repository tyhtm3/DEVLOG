package com.ssafy.devlog.dto;

public class Post {

	//post
	private int seq;
	private int seq_blog;
	private String title;
	private String regtime;
	private int disclosure; //1 전체공개 2 이웃공개(내가 추가한 이웃) 3 비공개
	private int like_count;
	private String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String img_url;
	
	//post_basic
	private String content;
		
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

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

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public int getDisclosure() {
		return disclosure;
	}

	public void setDisclosure(int disclosure) {
		this.disclosure = disclosure;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Post [seq=" + seq + ", seq_blog=" + seq_blog + ", title=" + title + ", regtime=" + regtime
				+ ", disclosure=" + disclosure + ", like_count=" + like_count + ", status=" + status + ", img_url="
				+ img_url + ", content=" + content + "]";
	}

	
	
}

