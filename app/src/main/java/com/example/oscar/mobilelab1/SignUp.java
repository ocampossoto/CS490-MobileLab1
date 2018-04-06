package com.example.oscar.mobilelab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SignUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Sign up button sends user to the index page
        Button submit_btn = findViewById(R.id.SignUpBtn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to index page and exit activity
                Intent intent = new Intent(SignUp.this, IndexPage.class);
                startActivity(intent);
                //todo: add functionality for saving data and
                //Implement : verify_data();
                finish();
            }
        });

        //Back button goes to previous page
        Button exit_btn = findViewById(R.id.Back_Button);

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //end activity
                finish();
            }
        });
    }


    private boolean verify_data() {
//        EditText FName = findViewById(R.id.First_Name);
//        EditText LName = findViewById(R.id.Last_Name);
//        EditText Month = findViewById(R.id.Month);
//        EditText Day = findViewById(R.id.Day);
//        EditText Year = findViewById(R.id.Year);
        // Add above for saving data

        //Get email and password for verification
        EditText Email = findViewById(R.id.Email_up);
        EditText Email_Verify = findViewById(R.id.Email_up_Verify);
        EditText Password = findViewById(R.id.Password_up);
        EditText Password_verify = findViewById(R.id.password_up_Veify);

        //check if they are the same or not.
        if (Email.getText().equals(Email_Verify.getText())) {
            if(Password.getText().equals(Password_verify.getText())){
                return true;
            }
            else{
                return false;
            }
        }else{
            return false;
        }
    }
}
