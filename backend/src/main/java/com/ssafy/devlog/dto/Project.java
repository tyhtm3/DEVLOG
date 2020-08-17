package com.ssafy.devlog.dto;

public class Project {

	//post
	private int seq;
	private int seq_blog;
	private String title;
	private String regtime;
	private int disclosure; //1 전체공개 2 이웃공개(내가 추가한 이웃) 3 비공개
	private int like_count;
	private String status;
	private String img_url;
	
	//post_project
	private String summary;
	private String start_date;
	private String finish_date;
	private String role;
	private String github_url;
	private String etc_url;
	private String rep_url;
	private String content;

	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getFinish_date() {
		return finish_date;
	}
	public void setFinish_date(String finish_date) {
		this.finish_date = finish_date;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getGithub_url() {
		return github_url;
	}
	public void setGithub_url(String github_url) {
		this.github_url = github_url;
	}
	public String getEtc_url() {
		return etc_url;
	}
	public void setEtc_url(String etc_url) {
		this.etc_url = etc_url;
	}
	public String getRep_url() {
		return rep_url;
	}
	public void setRep_url(String rep_url) {
		this.rep_url = rep_url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Project [seq=" + seq + ", seq_blog=" + seq_blog + ", title=" + title + ", regtime=" + regtime
				+ ", disclosure=" + disclosure + ", like_count=" + like_count + ", status=" + status + ", img_url="
				+ img_url + ", summary=" + summary + ", start_date=" + start_date + ", finish_date=" + finish_date
				+ ", role=" + role + ", github_url=" + github_url + ", etc_url=" + etc_url + ", rep_url=" + rep_url
				+ ", content=" + content + "]";
	}


	
}
