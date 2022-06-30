package com.example.reminder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CalRecy extends AppCompatActivity implements CalAdapter.CalAdapterEventListener {

    private CalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_recy);

        RecyclerView recyclerView = findViewById(R.id.recy_Cal);


        this.adapter = new CalAdapter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(this.adapter);

        recyclerView.setLayoutManager(layoutManager);


    }
    @Override
    public void onStart() {
        super.onStart();
        this.adapter.updateList(Database.getInstance(this).getCalDao().getAll());
    }

    @Override
    public void onCalClicked(long calId) {
        Cal_detail.onStartCal(this, calId);
    }

    @Override
    public void onCalLongClicked(long calId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.alertDialog);

        builder.setTitle("Delete Timetable");
        builder.setMessage("Do you really want to delete this Timetable?");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Cal cal = Database.getInstance(CalRecy.this).getCalDao().getById(calId);
                Database.getInstance(CalRecy.this).getCalDao().delete(cal);
                CalRecy.this.onStart();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    public void addH(View view){
        Intent intent = new Intent(this, CalendarActi.class);
        startActivity(intent);

    }
    public void addTaskH(View view){
        Intent intent = new Intent(this, AddTaskH.class);
        startActivity(intent);

    }
    public void Main(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}