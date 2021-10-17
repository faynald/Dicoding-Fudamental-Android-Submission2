package com.farhanrv.submission2githubuser.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ModelSearchItem implements Parcelable {

	@SerializedName("login")
	private final String login;

	@SerializedName("avatar_url")
	private final String avatarUrl;

	@SerializedName("id")
	private final int id;

	protected ModelSearchItem(Parcel in) {
		login = in.readString();
		avatarUrl = in.readString();
		id = in.readInt();
	}

	public static final Creator<ModelSearchItem> CREATOR = new Creator<ModelSearchItem>() {
		@Override
		public ModelSearchItem createFromParcel(Parcel in) {
			return new ModelSearchItem(in);
		}

		@Override
		public ModelSearchItem[] newArray(int size) {
			return new ModelSearchItem[size];
		}
	};

	public String getLogin(){
		return login;
	}

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public int getId(){
		return id;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(login);
		parcel.writeString(avatarUrl);
		parcel.writeInt(id);
	}
}