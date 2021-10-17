package com.farhanrv.submission2githubuser.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ModelFollowItem implements Parcelable {

	@SerializedName("login")
	private final String login;

	@SerializedName("avatar_url")
	private final String avatarUrl;

	@SerializedName("id")
	private final int id;

	protected ModelFollowItem(Parcel in) {
		login = in.readString();
		avatarUrl = in.readString();
		id = in.readInt();
	}

	public static final Creator<ModelFollowItem> CREATOR = new Creator<ModelFollowItem>() {
		@Override
		public ModelFollowItem createFromParcel(Parcel in) {
			return new ModelFollowItem(in);
		}

		@Override
		public ModelFollowItem[] newArray(int size) {
			return new ModelFollowItem[size];
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