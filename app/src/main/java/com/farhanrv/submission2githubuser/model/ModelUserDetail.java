package com.farhanrv.submission2githubuser.model;

import com.google.gson.annotations.SerializedName;

public class ModelUserDetail {

	@SerializedName("id")
	private int id;

	@SerializedName("login")
	private String login;

	@SerializedName("company")
	private String company;

	@SerializedName("public_repos")
	private int publicRepos;

	@SerializedName("followers")
	private int followers;

	@SerializedName("avatar_url")
	private String avatarUrl;

	@SerializedName("following")
	private int following;

	@SerializedName("name")
	private String name;

	@SerializedName("location")
	private String location;

	public String getLogin(){
		return login;
	}

	public String getCompany(){
		return company;
	}

	public int getId(){
		return id;
	}

	public int getPublicRepos(){
		return publicRepos;
	}

	public int getFollowers(){
		return followers;
	}

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public int getFollowing(){
		return following;
	}

	public String getName(){
		return name;
	}

	public String getLocation(){
		return location;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}