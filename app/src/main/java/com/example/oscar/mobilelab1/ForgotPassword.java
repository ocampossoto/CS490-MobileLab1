package com.example.oscar.mobilelab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //Submit button to request password reset.
        Button submit = findViewById(R.id.Request);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the ok button and the message to visible
                TextView message = findViewById(R.id.Message);
                message.setVisibility(View.VISIBLE);

                Button Exit_Btn = findViewById(R.id.Exit_btn);
                Exit_Btn.setVisibility(View.VISIBLE);
            }
        });

        //set up exit (ok) button functionality
        Button EXIT_BTN = findViewById(R.id.Exit_btn);
        EXIT_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish this activity to return to the previous one.
                finish();
            }
        });
    }
}
