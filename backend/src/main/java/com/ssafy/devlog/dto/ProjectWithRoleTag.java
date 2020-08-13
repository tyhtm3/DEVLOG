package com.ssafy.devlog.dto;

import java.util.List;

public class ProjectWithRoleTag {
	//post
		private int seq;
		private int seq_blog;
		private String title;
		private String regtime;
		private int disclosure; //1 전체공개 2 이웃공개(내가 추가한 이웃) 3 비공개
		private int like_count;
		private int comment_count;
		private String img_url;

		//post_project
		private String summary;
		private String start_date;
		private String finish_date;
		private String github_url;
		private String etc_url;
		private String rep_url;
		private String content;
		
		private List<PostTag> tags;
		private List<ProjectRole> roles;
		
		
		
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

		public int getComment_count() {
			return comment_count;
		}

		public void setComment_count(int comment_count) {
			this.comment_count = comment_count;
		}

		public String getImg_url() {
			return img_url;
		}

		public void setImg_url(String img_url) {
			this.img_url = img_url;
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

		public List<PostTag> getTags() {
			return tags;
		}

		public void setTags(List<PostTag> tags) {
			this.tags = tags;
		}

		public List<ProjectRole> getRoles() {
			return roles;
		}

		public void setRoles(List<ProjectRole> roles) {
			this.roles = roles;
		}

		public ProjectWithRoleTag() {
		}
		
		public ProjectWithRoleTag(Project p,List<PostTag> tags,List<ProjectRole> roles,int comment_coount) {
			this.seq = p.getSeq();
			this.seq_blog = p.getSeq_blog();
			this.title = p.getTitle();
			this.regtime = p.getRegtime();
			this.disclosure = p.getDisclosure();
			this.like_count = p.getLike_count();
			this.img_url = p.getImg_url();
			this.summary = p.getSummary();
			this.start_date = p.getStart_date();
			this.finish_date = p.getFinish_date();
			this.github_url = p.getGithub_url();
			this.etc_url = p.getEtc_url();
			this.rep_url = p.getRep_url();
			this.content = p.getContent();
			this.tags = tags;
			this.roles = roles;
			this.comment_count = comment_coount;
		}

		public ProjectWithRoleTag(int seq, int seq_blog, String title, String regtime, int disclosure, int like_count,
				int comment_count, String img_url, String summary, String start_date, String finish_date,
				String github_url, String etc_url, String rep_url, String content, List<PostTag> tags,
				List<ProjectRole> roles) {
			this.seq = seq;
			this.seq_blog = seq_blog;
			this.title = title;
			this.regtime = regtime;
			this.disclosure = disclosure;
			this.like_count = like_count;
			this.comment_count = comment_count;
			this.img_url = img_url;
			this.summary = summary;
			this.start_date = start_date;
			this.finish_date = finish_date;
			this.github_url = github_url;
			this.etc_url = etc_url;
			this.rep_url = rep_url;
			this.content = content;
			this.tags = tags;
			this.roles = roles;
		}
		
		
}
