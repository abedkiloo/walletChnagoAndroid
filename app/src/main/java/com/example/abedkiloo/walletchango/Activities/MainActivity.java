package com.example.abedkiloo.walletchango.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.example.abedkiloo.walletchango.Helpers.SessionManager;
import com.example.abedkiloo.walletchango.R;

public class MainActivity extends AppCompatActivity {

    AppCompatImageView splash_image;

    // Session Manager Class
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session Manager
        session = new SessionManager(getApplicationContext());

        splash_image = findViewById(R.id.splash_image);
        Animation animation = new AlphaAnimation((float) 0.5, 0); // Change alpha from fully visible to invisible
        animation.setDuration(600); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter
        // animation
        // rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation
        // infinitely
        animation.setRepeatMode(Animation.REVERSE);
        splash_image.startAnimation(animation);

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 600);
    }
}
