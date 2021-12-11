package com.farhanrv.submission2githubuser.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class ModelUserItem implements Parcelable {

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "db_id")
	private long dbId;

	@SerializedName("login")
	@ColumnInfo(name = "login")
	private String login;

	@SerializedName("avatar_url")
	@ColumnInfo(name = "avatar_url")
	private String avatarUrl;

	@SerializedName("id")
	@ColumnInfo(name = "id")
	private int id;

	public ModelUserItem() {
	}

	public ModelUserItem(String login, String avatarUrl, int id) {
		this.login = login;
		this.avatarUrl = avatarUrl;
		this.id = id;
	}

	protected ModelUserItem(Parcel in) {
		login = in.readString();
		avatarUrl = in.readString();
		id = in.readInt();
	}

	public static final Creator<ModelUserItem> CREATOR = new Creator<ModelUserItem>() {
		@Override
		public ModelUserItem createFromParcel(Parcel in) {
			return new ModelUserItem(in);
		}

		@Override
		public ModelUserItem[] newArray(int size) {
			return new ModelUserItem[size];
		}
	};

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDbId() {
		return dbId;
	}

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