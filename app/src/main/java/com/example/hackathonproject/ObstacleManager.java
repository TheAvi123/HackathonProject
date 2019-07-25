package com.example.hackathonproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ObstacleManager {
    private ArrayList<Obstacle> obstacles;
    private int missileFrequency; // take this out later
    private long startTime;
    private long timer;
    final Handler spawnHandler = new Handler();
    private ArrayList<Obstacle> obstaclesNeedToAdd;
    private int previousNum = 0;


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

//    private void populateObstacles() { // missiles need to come from top right and left of the screen
//        int currY = -5 * Constants.SCREEN_HEIGHT / 4;
//        while (currY < 0) { // should get rid of missiles based on touch event {
//            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));
//            obstacles.add(new Missile(10, xStart, currY));
//            currY += Missile.height + missileFrequency;
////            obstacles.remove(obstacles.size() - 1);
//
//        }
//
//    }

    public Runnable SpawnEnemies = new Runnable() {
        @Override
        public void run() {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));
//            needToAddObstacle(new Missile(10, xStart, 0));

//            obstacles.add(new Missile(10, xStart, 0));
//            spawnHandler.postDelayed(this, 2000);
        }
    };

    public void update() {
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        long currTime = System.currentTimeMillis() - timer;
        int currNum = (int) Math.floor(currTime / 1000);
        long missileFreq = 1000;
        if (previousNum != currNum) {
            previousNum = currNum;
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));
            obstacles.add(new Missile(10, xStart, 0));
        }
//        if (obstacles.size() < 5) {
//            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));
//            obstacles.add(new Missile(10, xStart, 0));
//        }
        spawnHandler.postDelayed(SpawnEnemies, 1000);
        for (Obstacle ob : obstacles) {
            ob.update(elapsedTime);
            if (ob.getRectangle().top > Constants.SCREEN_HEIGHT) {
//                obstacles.remove(ob); // not good
//                System.out.println("REMOVED AN OBSTACLE ONLY " + obstacles.size() + " LEFT");
            }
        }
    }


    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
    }
}
