package com.farhanrv.submission2githubuser.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelUserResponse {

	@SerializedName("items")
	private List<ModelUserItem> items;

	public List<ModelUserItem> getItems(){
		return items;
	}
}