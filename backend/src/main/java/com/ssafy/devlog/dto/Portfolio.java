package com.ssafy.devlog.dto;

public class Portfolio {

	//post
	private int seq;
	private int seq_blog;
	private String title;
	private String regtime;
	private int disclosure; //1 전체공개 2 이웃공개(내가 추가한 이웃) 3 비공개
	private int like_count;
	private String img_url;
	private int representation;
	
	public int getRepresentation() {
		return representation;
	}
	public void setRepresentation(int representation) {
		this.representation = representation;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	//portfolio
	private String name;
	private String profile_img_url;
	private String github_url;
	private String birthday;
	private String content;
	private String tel;
	private String email;
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getGithub_url() {
		return github_url;
	}
	public void setGithub_url(String github_url) {
		this.github_url = github_url;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Portfolio [seq=" + seq + ", seq_blog=" + seq_blog + ", title=" + title + ", regtime=" + regtime
				+ ", disclosure=" + disclosure + ", like_count=" + like_count + ", img_url=" + img_url
				+ ", representation=" + representation + ", name=" + name + ", profile_img_url=" + profile_img_url
				+ ", github_url=" + github_url + ", birthday=" + birthday + ", content=" + content + ", tel=" + tel
				+ ", email=" + email + "]";
	}


	
	
	
	
}
