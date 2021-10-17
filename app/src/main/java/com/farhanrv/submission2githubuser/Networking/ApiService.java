package com.farhanrv.submission2githubuser.Networking;

import com.farhanrv.submission2githubuser.Model.ModelFollowItem;
import com.farhanrv.submission2githubuser.Model.ModelSearchResponse;
import com.farhanrv.submission2githubuser.Model.ModelUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String strAuthorization = "Authorization: token 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";

    @GET("search/users")
    @Headers(strAuthorization)
    Call<ModelSearchResponse> searchUser(@Query("q") String query);

    @GET("users/{username}")
    @Headers(strAuthorization)
    Call<ModelUser> getDetailUser(@Path("username") String username);

    @GET("users/{username}/following")
    @Headers(strAuthorization)
    Call<ArrayList<ModelFollowItem>> getFollowingUser(@Path("username") String username);

    @GET("users/{username}/followers")
    @Headers(strAuthorization)
    Call<ArrayList<ModelFollowItem>> getFollowerUser(@Path("username") String username);

}
//
