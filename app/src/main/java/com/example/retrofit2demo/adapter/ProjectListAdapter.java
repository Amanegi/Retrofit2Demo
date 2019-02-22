package com.example.retrofit2demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofit2demo.R;
import com.example.retrofit2demo.model.Project;

import java.util.ArrayList;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.MyViewHolder> {
    Context context;
    ArrayList<Project> projectArrayList;

    public ProjectListAdapter(Context context, ArrayList<Project> projectArrayList) {
        this.context = context;
        this.projectArrayList = projectArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txtTitle.setText(projectArrayList.get(i).getTitle());
        myViewHolder.txtDes.setText(projectArrayList.get(i).getDescription());

    }

    @Override
    public int getItemCount() {
        return projectArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDes, txtTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDes = itemView.findViewById(R.id.txtDes);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
