package com.ssafy.devlog.dto;

public class User {
	private int seq;
	private String id;
	private String password;
	private String email;
	private String nickname;
	private String name;
	private String tel;
	private String profile_img_url;
	private String github_url;
	private String birthday;
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public User() {
	}

	public User(int seq, String id, String password, String email, String nickname, String name, String tel,
			String profile_img_url, String github_url, String birthday) {
		this.seq = seq;
		this.id = id;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.name = name;
		this.tel = tel;
		this.profile_img_url = profile_img_url;
		this.github_url = github_url;
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [seq=" + seq + ", id=" + id + ", password=" + password + ", email=" + email + ", nickname="
				+ nickname + ", name=" + name + ", tel=" + tel + ", profile_img_url=" + profile_img_url
				+ ", github_url=" + github_url + ", birthday=" + birthday + "]";
	}

}
