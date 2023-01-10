package com.example.reminder.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;


import android.util.Patterns;
import android.view.View;

import android.widget.Toast;

import com.example.reminder.R;
import com.example.reminder.data.User;
import com.example.reminder.data.database.Database;

public class SignupActivity extends AppCompatActivity {

    private EditText  emailEdit, passEdit, rePassEdit;
    final int MIN_PASSWORD_LENGTH = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       /* viewInitializations();*/
    }

   /* void viewInitializations() {

        this.emailEdit = findViewById(R.id.emailEdit);
        this.passEdit = findViewById(R.id.passEdit);
        this.rePassEdit = findViewById(R.id.rePassEdit);

        // To show back button in actionbar
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }*/

    boolean validateInput() {
        if (emailEdit.getText().toString().equals("")) {
            emailEdit.setError("Please Enter Email");
            return false;
        }
        if (passEdit.getText().toString().equals("")) {
            passEdit.setError("Please Enter Password");
            return false;
        }

        if (rePassEdit.getText().toString().equals("")) {
            rePassEdit.setError("Please Enter Repeat Password");
            return false;
        }


        if (!isEmailValid(emailEdit.getText().toString())) {
            emailEdit.setError("Please Enter Valid Email");
            return false;
        }


        if (passEdit.getText().length() < MIN_PASSWORD_LENGTH) {
            passEdit.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters");
            return false;
        }


        if (!passEdit.getText().toString().equals(rePassEdit.getText().toString())) {
            rePassEdit.setError("Password does not match");
            return false;
        }
        return true;
        }



    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



    public void performSignUp (View v) {
        if (validateInput()) {

            // Input is valid, here send data to your server


            String email = emailEdit.getText().toString();
            String password = passEdit.getText().toString();
            String repeatPassword = rePassEdit.getText().toString();

            User userClass = new User(0,email,password);

            Database.getInstance(this).getUserDao().insert(userClass);

            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();


        }
    }


}