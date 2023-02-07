package com.example.calldatafromapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceholder {
    @GET("posts")
    Call<List<com.example.calldatafromapi.Post>> getPost();

    @GET("comments")
    Call<List<com.example.calldatafromapi.Comment>> getComments(@Query("postId") int postId);

    @POST("posts")
    Call<com.example.calldatafromapi.Post> createPost(@Body com.example.calldatafromapi.Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<com.example.calldatafromapi.Post> createPost(@Field("userId") String userId , @Field("title") String title , @Field("body") String text);

    @PUT("posts/{id}")
    Call<com.example.calldatafromapi.Post> putPost(@Path("id") int id , @Body com.example.calldatafromapi.Post post);

    @PATCH("posts/{id}")
    Call<com.example.calldatafromapi.Post> patchPost(@Path("id") int id , @Body com.example.calldatafromapi.Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
