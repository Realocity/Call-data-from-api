package com.example.calldatafromapi;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    JSONPlaceholder jsonPlaceholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycerlview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholder = retrofit.create(JSONPlaceholder.class);

        getPost();
        getComments();

        createPost();

      //  updatePost();

       deletePost();

    }

    private void deletePost() {

        Call<Void> call = jsonPlaceholder.deletePost(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    return;
                }
                Toast.makeText(MainActivity.this, "Deleted Successfully : " + response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void updatePost() {
        com.example.calldatafromapi.Post post = new com.example.calldatafromapi.Post("13" , "new title" , null);
        Call<com.example.calldatafromapi.Post> call = jsonPlaceholder.patchPost(2 , post);
        call.enqueue(new Callback<com.example.calldatafromapi.Post>() {
            @Override
            public void onResponse(Call<com.example.calldatafromapi.Post> call, Response<com.example.calldatafromapi.Post> response) {
                if (!response.isSuccessful()){
                    return;
                }

                List<com.example.calldatafromapi.Post> postList = new ArrayList<>();
                postList.add(response.body());
                com.example.calldatafromapi.PostAdapter adapter = new com.example.calldatafromapi.PostAdapter(MainActivity.this , postList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<com.example.calldatafromapi.Post> call, Throwable t) {

            }
        });
    }


    private void createPost(){

     //   Post post = new Post("18" , "First Title" , "First Text");

        Call<com.example.calldatafromapi.Post> call = jsonPlaceholder.createPost("13" , "Second Title" , "Second Text");

        call.enqueue(new Callback<com.example.calldatafromapi.Post>() {
            @Override
            public void onResponse(Call<com.example.calldatafromapi.Post> call, Response<com.example.calldatafromapi.Post> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code() , Toast.LENGTH_SHORT).show();
                    return;
                }

                List<com.example.calldatafromapi.Post> postList = new ArrayList<>();
                postList.add(response.body());

                com.example.calldatafromapi.PostAdapter postAdapter = new com.example.calldatafromapi.PostAdapter(MainActivity.this , postList);
                recyclerView.setAdapter(postAdapter);

                Toast.makeText(MainActivity.this, response.code() + " Response", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<com.example.calldatafromapi.Post> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getComments(){
        Call<List<com.example.calldatafromapi.Comment>> call = jsonPlaceholder.getComments(2);

        call.enqueue(new Callback<List<com.example.calldatafromapi.Comment>>() {
            @Override
            public void onResponse(Call<List<com.example.calldatafromapi.Comment>> call, Response<List<com.example.calldatafromapi.Comment>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code() , Toast.LENGTH_SHORT).show();
                    return;
                }

                List<com.example.calldatafromapi.Comment> comments = response.body();
                com.example.calldatafromapi.CommentAdapter commentAdapter = new com.example.calldatafromapi.CommentAdapter(MainActivity.this , comments);
                recyclerView.setAdapter(commentAdapter);
            }

            @Override
            public void onFailure(Call<List<com.example.calldatafromapi.Comment>> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPost(){
        Call<List<com.example.calldatafromapi.Post>> call = jsonPlaceholder.getPost();
        call.enqueue(new Callback<List<com.example.calldatafromapi.Post>>() {
            @Override
            public void onResponse(Call<List<com.example.calldatafromapi.Post>> call, Response<List<com.example.calldatafromapi.Post>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<com.example.calldatafromapi.Post> postList = response.body();
                com.example.calldatafromapi.PostAdapter postAdapter = new com.example.calldatafromapi.PostAdapter(MainActivity.this , postList);
                recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<List<com.example.calldatafromapi.Post>> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}