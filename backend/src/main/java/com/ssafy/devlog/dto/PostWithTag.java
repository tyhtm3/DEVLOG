package com.ssafy.devlog.dto;

import java.util.List;

public class PostWithTag {

	//post
	private int seq;
	private int seq_blog;
	private String title;
	private String regtime;
	private int disclosure; //1 전체공개 2 이웃공개(내가 추가한 이웃) 3 비공개
	private int like_count;
	private int comment_count;
	
	private String img_url;
	private String nickname;
	private String id;
	private List<PostTag> tags;
	//post_basic
	private String content;
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
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public List<PostTag> getTags() {
		return tags;
	}
	public void setTags(List<PostTag> tags) {
		this.tags = tags;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
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
	public PostWithTag() {
	}
	public PostWithTag(Post p,List<PostTag> tags,int comment_count ) {
		this.seq = p.getSeq();
		this.seq_blog = p.getSeq_blog();
		this.title = p.getTitle();
		this.regtime = p.getRegtime();
		this.disclosure = p.getDisclosure();
		this.like_count = p.getLike_count();
		this.img_url = p.getImg_url();
		this.content = p.getContent();
		this.tags = tags;
		this.comment_count = comment_count;
	}
	public PostWithTag(Post p,List<PostTag> tags,int comment_count,String id, String nickname ) {
		this.seq = p.getSeq();
		this.seq_blog = p.getSeq_blog();
		this.title = p.getTitle();
		this.regtime = p.getRegtime();
		this.disclosure = p.getDisclosure();
		this.like_count = p.getLike_count();
		this.img_url = p.getImg_url();
		this.content = p.getContent();
		this.tags = tags;
		this.comment_count = comment_count;
		this.id = id;
		this.nickname = nickname;
	}
	public PostWithTag(int seq, int seq_blog, String title, String regtime, int disclosure, int like_count,
			int comment_count, String img_url, String nickname, String id, List<PostTag> tags, String content) {
		this.seq = seq;
		this.seq_blog = seq_blog;
		this.title = title;
		this.regtime = regtime;
		this.disclosure = disclosure;
		this.like_count = like_count;
		this.comment_count = comment_count;
		this.img_url = img_url;
		this.nickname = nickname;
		this.id = id;
		this.tags = tags;
		this.content = content;
	}

	
	
}

