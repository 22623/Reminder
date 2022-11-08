package com.example.reminder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements TaskAdapter.TaskAdapterEventListener {


    private TaskAdapter adapter;
    private List<Task> taskList;
    private Context context;
    private TaskAdapter.TaskAdapterEventListener listener;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!SessionManager.sessionExists(this)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        adapter = new TaskAdapter(listener,taskList,context);
        recyclerView = findViewById(R.id.recycleView);
        this.context=this;


        adapter = new TaskAdapter(listener,taskList,context);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
       //fetchTask();
    }


    @Override
    public void onStart() {
        super.onStart();
        //this.adapter.updateList(Database.getInstance(this).getTaskDao().getAll());
    }



    @Override
    public void onTaskClicked(long taskId) {
        TaskDetail.onStarting(this, taskId);
    }

    @Override
    public void onTaskLongClicked(long taskId) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.alertDialog);

        builder.setTitle("Delete Task");
        builder.setMessage("Do you really want to delete this Task?");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Task task = Database.getInstance(MainActivity.this).getTaskDao().getById(taskId);
                Database.getInstance(MainActivity.this).getTaskDao().delete(task);
                MainActivity.this.onStart();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void addTaskH(View view){
        Intent intent = new Intent(this, AddTaskH.class);
        startActivity(intent);

    }

    private void fetchTask(){
        Call<List<Task>> call = RetrofitClient.service().getTaskService();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {


            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

            }
        });
    }


    public void cal(View view) {
        Intent intent = new Intent(this, CalRecy.class);
        startActivity(intent);

    }
}