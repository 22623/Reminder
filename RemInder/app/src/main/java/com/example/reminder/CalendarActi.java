package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class CalendarActi extends AppCompatActivity {

    private TextView tv;
    private EditText calName;
    private EditText calText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        this.tv = findViewById(R.id.textViewcal);
        this.calName = findViewById(R.id.NameCal);
        this.calText = findViewById(R.id.DescCal);
    }

    public void calendarAdd(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(CalendarActi.this,R.style.CalenderViewCustom, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year +"/"+month+"/"+dayOfMonth;
                tv.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public void AddCal(View view) {
        String date = this.tv.getText().toString();
        String name = this.calName.getText().toString();
        String text = this.calText.getText().toString();

        Cal calClass = new Cal(0,text,date,name);
        Database.getInstance(this).getCalDao().insert(calClass);

        finish();
    }
    public void cancelCal(View view){
        finish();
    }
}