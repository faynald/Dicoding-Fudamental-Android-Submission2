package com.farhanrv.submission2githubuser.networking;

import com.farhanrv.submission2githubuser.BuildConfig;
import com.farhanrv.submission2githubuser.model.ModelFollowItem;
import com.farhanrv.submission2githubuser.model.ModelUserResponse;
import com.farhanrv.submission2githubuser.model.ModelUserDetail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String strAuthorization = BuildConfig.KEY;

    @GET("search/users")
    @Headers(strAuthorization)
    Call<ModelUserResponse> searchUser(@Query("q") String query);

    @GET("users/{username}")
    @Headers(strAuthorization)
    Call<ModelUserDetail> getDetailUser(@Path("username") String username);

    @GET("users/{username}/following")
    @Headers(strAuthorization)
    Call<ArrayList<ModelFollowItem>> getFollowingUser(@Path("username") String username);

    @GET("users/{username}/followers")
    @Headers(strAuthorization)
    Call<ArrayList<ModelFollowItem>> getFollowerUser(@Path("username") String username);

}
