package com.example.lessontwoandroid;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        findViewById(R.id.dark).setOnClickListener(v -> {
            this.setTheme(R.style.ThemeOverlay_AppCompat_Dark);

        });

        findViewById(R.id.light).setOnClickListener(v -> {
            this.setTheme(R.style.Theme_AppCompat_Light);

        });
    }
}