package com.example.oscar.mobilelab1;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.services.AccountService;

import java.util.Arrays;

public class SignIn extends AppCompatActivity {

    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("Y5vCHFsYk7qg8XlRYYeqAHj39", "nstq9qGmCeJoWaLqSDwwaPM0vO8kr6QcWhAosWcHZlPTE3d8fN"))
                .debug(true)
                .build();
        Twitter.initialize(config);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        setContentView(R.layout.activity_sign_in);

        // Build a GoogleSignInClient with the options specified by gso.
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(SignIn.this, gso);

        LoginUsingTwitterButton();

        //LoginUsingLoginButton();
        LoginUsingFacbookButton();

        //Sign in button send user to index page.
        Button sign_in = findViewById(R.id.SignIn_btn);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to index page
                ToIndex();
                //todo: verify sign in data and sign them into account.

            }
        });

        //forgot button sends user to forgot password activity
        Button forgot = findViewById(R.id.Forgot_Password);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send the user to forgot password activity
                Intent intent = new Intent(SignIn.this, ForgotPassword.class);
                startActivity(intent);
                finish(); //end this activity
            }
        });

        //Google login button that looks like image
        SignInButton google_btn = findViewById(R.id.Google_btn);
        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 9001);
                ToIndex(true);
            }
        });

    }
    //redirect to index page
    private void ToIndex(boolean now) {
        if(now){
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            if(account != null){
                Intent intent = new Intent(SignIn.this, IndexPage.class);
                startActivity(intent);
                finish();
            }
            else{
                return;
            }

        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SignIn.this, IndexPage.class);
                    startActivity(intent);
                    finish();
                }
            }, 1500);
        }

    }

    //return to homepage
    //Only for twitter since there is an error with it not working after hitting cancel.
    private void GoToHome(){
        Intent intent = new Intent(SignIn.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }


    /*Code to login using Facebook */
    private void LoginUsingFacbookButton() {
        CallbackManager callbackManager = CallbackManager.Factory.create();
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //go to index page.
                ToIndex(false);
            }
            @Override
            public void onCancel() {

            }
            @Override
            public void onError(FacebookException error) {

            }};
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile"));
        loginButton.registerCallback(callbackManager, callback);
    }

    //code to sign in using Twitter
    private void LoginUsingTwitterButton() {
        loginButton = findViewById(R.id.Twitter_btn);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                requestEmailAddress(SignIn.this, result.data);
                //Go to index page
                ToIndex(false);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                // Upon error, show a toast message indicating that authorization request failed.
                Toast.makeText(getApplicationContext(), exception.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void requestEmailAddress(final Context context, TwitterSession session) {

        TwitterAuthClient authClient = new TwitterAuthClient();
        authClient.requestEmail(session, new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                // Do something with the result, which provides the email address
                Toast.makeText(context, result.data + " is logged in", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result to the saveSession button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
