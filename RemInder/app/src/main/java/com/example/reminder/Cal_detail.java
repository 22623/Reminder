package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Cal_detail extends AppCompatActivity {

    public static final String KEY_ID = "calId";
    private static final String KEY_INDEX = "index";
    private long calId;
    private CalAdapter calAdapter;
    private EditText textViewTitle;
    private EditText textViewDesc;
    private TextView textViewDate;


    public static void onStartCal(Context context, long calId){
        Intent intent = new Intent(context, Cal_detail.class);
        intent.putExtra(KEY_INDEX, calId);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_detail);

        if (getIntent().getExtras() != null) {
            this.calId= getIntent().getExtras().getLong(KEY_INDEX, -1);
            if (calId == -1) finish();
            Cal cal = Database.getInstance(this).getCalDao().getById(calId);

            this.textViewTitle = findViewById(R.id.Name_Cal_detail);
            this.textViewDesc = findViewById(R.id.Desc_Cal_Detail);
            this.textViewDate = findViewById(R.id.Date_Cal_Detail);


            this.textViewTitle.setText(cal.getCalName());
            this.textViewDesc.setText(cal.getCalText());
            this.textViewDate.setText(cal.getCalDate());

        } else {
            finish();
        }


    }

    public void ReturnMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void cal(View view) {
        Intent intent = new Intent(this, CalRecy.class);
        startActivity(intent);

    }
    public void updateCal(View v){
        String date = this.textViewTitle.getText().toString();
        String name = this.textViewDesc.getText().toString();
        String text = this.textViewDate.getText().toString();


        Cal calClass = new Cal(0,text,date,name);

        Database.getInstance(this).getCalDao().update(calClass);

        finish();
    }
}