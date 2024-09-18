package com.example.android_authentication_using_volley_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; // Tag for logging
    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        im = (ImageView) findViewById(R.id.image1);
        android.view.animation.Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        im.startAnimation(a);

        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 4 seconds
                    sleep(2 * 1000);

                    // After 4 seconds redirect to another intent
                    Intent i = new Intent(getApplicationContext(), Login_activity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);

                    // Remove activity
                    finish();
                } catch (Exception e) {
                    Log.e(TAG, "Error in background thread", e); // Log the error
                }
            }
        };

        background.start(); // Start the thread
    }
}
