package com.example.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelSelectionActivity extends AppCompatActivity {
    Button level1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelselectionactivity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);

        level1 = findViewById(R.id.Level1);
        createOnClickListeners();
    }

    private void createOnClickListeners() {
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLevelOne();
            }
        });
    }

    private void startLevelOne() {
        Intent beginLevelOne = new Intent (this, LevelOne.class);
        startActivity(beginLevelOne);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
