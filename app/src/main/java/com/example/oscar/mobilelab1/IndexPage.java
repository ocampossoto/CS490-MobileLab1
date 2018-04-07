package com.example.oscar.mobilelab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IndexPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);

        //Go home button sends user to the first activity
        Button Go_Home = findViewById(R.id.Go_Home);
        Go_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to welcome activity
                Intent intent = new Intent(IndexPage.this, WelcomeActivity.class);
                startActivity(intent);
                finish(); //end activity
            }
        });

        //Settings button sends user to settings page
        Button Settings_btn = findViewById(R.id.Settings_btn);
        Settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to settings page
                Intent intent = new Intent(IndexPage.this, Settings.class);
                startActivity(intent);
            }
        });

        //help button sends user to help page
        Button Help_btn = findViewById(R.id.Help_Button);
        Help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to help activity
                Intent intent = new Intent(IndexPage.this, Help.class);
                startActivity(intent);
            }
        });

    }
}
