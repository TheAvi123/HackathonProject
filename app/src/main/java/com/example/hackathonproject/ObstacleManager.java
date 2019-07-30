package com.example.hackathonproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;

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




    public ObstacleManager() {

        obstacles = new ArrayList<>();
        missileFrequency = new Random().nextInt(100);
//        populateObstacles();
        timer = System.currentTimeMillis();
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
//        if (obstacles.size() == 0) {
            if (previousNum != currNum) {
                previousNum = currNum;
                obstacles.add(new Missile(playersPos));
                System.out.println("added obstacle, " + obstacles.size() + " are now active");
            }
//        }

//        System.out.println(obstacles.get(0).getRectangle());
//        System.out.println(obstacles.get(0).getRectangle().right + " this is the right point");
//        System.out.println(obstacles.get(0).getRectangle().top + " this is the top point");
//        System.out.println(obstacles.get(0).getRectangle().bottom+ " this is the bottom point");
//        System.out.println(obstacles.get(0).getRectangle().left  + " this is the left point");

//        spawnHandler.postDelayed(SpawnEnemies, missileFreq);
        ArrayList<Obstacle> obstacleCopy = obstacles;
        for (int i = 0; i < obstacleCopy.size(); i++) {
            obstacleCopy.get(i).update(elapsedTime);
            if (obstacleCopy.get(i).getRectangle().top > Constants.SCREEN_HEIGHT) {
                obstacles.remove(i);
                System.out.println("REMOVED AN OBSTACLE ONLY " + obstacles.size() + " LEFT");

            }

        }
    }


    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        playersPos = (Point) o;
    }
}
