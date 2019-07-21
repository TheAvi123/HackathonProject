package com.example.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startGame,quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame = findViewById(R.id.GameStart);
        quit = findViewById(R.id.ExitGame);
        createOnClickListeners();
    }

    private void createOnClickListeners() {
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMenuScreen();
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void startMenuScreen() {
        Intent goToMenu = new Intent(this, MainActivity2.class);
        startActivity(goToMenu);
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
