package com.ssafy.devlog.dto;

import java.util.List;

public class Post {

	//post
	private int seq;
	private int seq_blog;
	private String title;
	private String regtime;
	
	//post_basic
	private String content;
	
	//post_tag
	private List<PostTag> tag;
	
	//post_comment
	private List<PostComment> comment;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<PostTag> getTag() {
		return tag;
	}

	public void setTag(List<PostTag> tag) {
		this.tag = tag;
	}

	public List<PostComment> getComment() {
		return comment;
	}

	public void setComment(List<PostComment> comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Post [seq=" + seq + ", seq_blog=" + seq_blog + ", title=" + title + ", regtime=" + regtime
				+ ", content=" + content + ", tag=" + tag + ", comment=" + comment + "]";
	}
	
}

