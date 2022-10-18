package com.example.reminder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements TaskAdapter.TaskAdapterEventListener {


    private TaskAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (!SessionManager.sessionExists(this)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

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