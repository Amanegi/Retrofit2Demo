package com.example.retrofit2demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.retrofit2demo.R;
import com.example.retrofit2demo.adapter.ProjectListAdapter;
import com.example.retrofit2demo.model.Project;
import com.example.retrofit2demo.model.ProjectListResponse;
import com.example.retrofit2demo.network.API;
import com.example.retrofit2demo.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProjectActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ApiInterface apiInterface;
    Retrofit retrofit;
    ArrayList<Project> projectArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(layoutManager);
        projectArrayList = new ArrayList<>();

        retrofit = API.getRetrofitInstance();
        apiInterface = retrofit.create(ApiInterface.class);

        Call<ProjectListResponse> call = apiInterface.getProjects();

        call.enqueue(new Callback<ProjectListResponse>() {
            @Override
            public void onResponse(Call<ProjectListResponse> call, Response<ProjectListResponse> response) {
                Toast.makeText(ProjectActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                projectArrayList = (ArrayList<Project>) response.body().getResponseData();
                ProjectListAdapter projectListAdapter = new ProjectListAdapter(ProjectActivity.this, projectArrayList);
                recyclerView.setAdapter(projectListAdapter);
            }

            @Override
            public void onFailure(Call<ProjectListResponse> call, Throwable t) {

            }
        });

    }
}
