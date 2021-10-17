package com.farhanrv.submission2githubuser.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelSearchResponse{

	@SerializedName("items")
	private List<ModelSearchItem> items;

	public List<ModelSearchItem> getItems(){
		return items;
	}
}