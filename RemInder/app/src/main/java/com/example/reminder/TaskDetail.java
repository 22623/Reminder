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

    private EditText textViewTitle;
    private EditText textViewDesc;




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

            this.textViewTitle = findViewById(R.id.textViewTitle);
            this.textViewDesc = findViewById(R.id.textViewDesc);


            this.textViewTitle.setText(task.getTaskName());
            this.textViewDesc.setText(task.getTaskText());

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
    public void updateTask(View view){
        String name = this.textViewTitle.getText().toString();
        String text = this.textViewDesc.getText().toString();

        Task taskClass = new Task(taskId,name,text);
        Database.getInstance(this).getTaskDao().update(taskClass);

        finish();


    }
    public void cancelTask(View view){
        finish();
    }
}