package com.example.oscar.mobilelab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Sign up button takes user to sign up page
        final Button Signup = findViewById(R.id.SignUpButton);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to sign up page
                Intent signupIntent = new Intent(WelcomeActivity.this, SignUp.class);
                startActivity(signupIntent);
            }
        });

        //Sign in button takes user to sign in page
        final Button Signin = findViewById(R.id.SignInButton);
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send user to sign in page
                Intent signinIntent = new Intent(WelcomeActivity.this, SignIn.class);
                startActivity(signinIntent);
            }
        });
    }
}
