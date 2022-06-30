package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddTaskH extends AppCompatActivity {


    private EditText nameTask;
    private EditText textTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_h);

        this.nameTask = findViewById(R.id.task_Name);
        this.textTask = findViewById(R.id.task_Text);



    }

    public void saveTask(View view){
        String name = this.nameTask.getText().toString();
        String text = this.textTask.getText().toString();

        Task taskClass = new Task(0,name,text);
        Database.getInstance(this).getTaskDao().insert(taskClass);

        finish();


    }
    public void cancelTask(View view){
        finish();
    }

}