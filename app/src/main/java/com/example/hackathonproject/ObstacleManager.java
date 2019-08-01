package com.example.hackathonproject;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ObstacleManager implements Observer {
    private ArrayList<Obstacle> obstacles;
    private int missileFrequency; // take this out later
    private long startTime;
    private long timer;
    final Handler spawnHandler = new Handler();
    private ArrayList<Obstacle> obstaclesNeedToAdd;
    private int previousNum = 0;
    protected Point playersPos;
    int scoreNum;
    TextView scoreTxt;




    public ObstacleManager() {

        scoreNum = 0;
        obstacles = new ArrayList<>();
        missileFrequency = new Random().nextInt(100);
//        populateObstacles();
        timer = System.currentTimeMillis();
        scoreTxt = (TextView) ((Activity)Constants.CURRENT_CONTEXT).findViewById(R.id.text);

    }

    public boolean playerCollide(Player player) {
        for (Obstacle ob : obstacles) {
            if (ob.collidingWithPlayer(player))
                return true;
        }
        return false;
    }


//    public Runnable SpawnEnemies = new Runnable() {
//        @Override
//        public void run() {
//            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));
//        }
//    };

    public void update() {
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        long currTime = System.currentTimeMillis() - timer;
        int currNum = (int) Math.floor(currTime / 1000);
//        scoreTxt.setText(scoreNum);
//        if (obstacles.size() == 0) {
            if (previousNum != currNum) {
                previousNum = currNum;
                obstacles.add(new Missile(playersPos));
                System.out.println("added obstacle, " + obstacles.size() + " are now active");
            }
        ArrayList<Obstacle> obstacleCopy = obstacles;
        for (int i = 0; i < obstacleCopy.size(); i++) {
            obstacleCopy.get(i).update(elapsedTime);
            if (obstacleCopy.get(i).getRectangle().top > Constants.SCREEN_HEIGHT) {
                obstacles.remove(i);
                scoreNum++;
                System.out.println("REMOVED AN OBSTACLE ONLY " + obstacles.size() + " LEFT");

            }

        }
    }


    public void draw(Canvas canvas) {
        Paint txtPaint = new Paint();
        txtPaint.setTextSize(50);
        canvas.drawText("Score: " + Integer.toString(scoreNum),50,50,txtPaint);
        for (Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        playersPos = (Point) o;
    }
}
