package com.example.reminder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity implements TaskAdapter.TaskAdapterEventListener {


    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycleView);


        this.adapter = new TaskAdapter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(this.adapter);

        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void onStart() {
        super.onStart();
        this.adapter.updateList(Database.getInstance(this).getTaskDao().getAll());
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




    public void cal(View view) {
        Intent intent = new Intent(this, CalRecy.class);
        startActivity(intent);

    }
}