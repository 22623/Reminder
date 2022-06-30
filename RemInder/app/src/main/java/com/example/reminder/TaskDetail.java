package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class TaskDetail extends AppCompatActivity {

    public static final String KEY_ID = "taskId";
    private static final String KEY_INDEX = "index";
    private long taskId;
    private TaskAdapter taskAdapter;




    public static void onStarting(Context context, long taskId){
        Intent intent = new Intent(context, TaskDetail.class);
        intent.putExtra(KEY_INDEX, taskId);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);



        if (getIntent().getExtras() != null) {
            this.taskId= getIntent().getExtras().getLong(KEY_INDEX, -1);
            if (taskId == -1) finish();
            Task task = Database.getInstance(this).getTaskDao().getById(taskId);

            TextView textViewTitle = findViewById(R.id.textViewTitle);
            TextView textViewDesc = findViewById(R.id.textViewDesc);


            textViewTitle.setText(task.getTaskName());
            textViewDesc.setText(task.getTaskText());

        } else {
            finish();
        }
    }
    public void cal(View view) {
        Intent intent = new Intent(this, CalRecy.class);
        startActivity(intent);

    }
    public void Main(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}