package com.example.hackathonproject;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class ObstacleManager {
    private ArrayList<Obstacle> obstacles;
    private int missileFrequency; // take this out later
    private long startTime;
    private long timer;

    public ObstacleManager() {

        obstacles = new ArrayList<>();
        missileFrequency = new Random().nextInt(100) + 100;
        populateObstacles();
        timer = System.currentTimeMillis();
    }

    private void populateObstacles() { // missles need to come from top right and left of the screen
        int currY = -5 * Constants.SCREEN_HEIGHT / 4;
        while (currY < 0) { // should get rid of missles based on touch event {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));
            obstacles.add(new Missile(10, xStart, currY));
            currY += Missile.height + missileFrequency;
//            obstacles.remove(obstacles.size() - 1);

        }

    }

    public void update() {
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        if (timer >= 1000) {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));
            obstacles.add(new Missile(10,xStart,0));
        }
        for (Obstacle ob : obstacles) {
            ob.update(elapsedTime);
            if (ob.getRectangle().top > Constants.SCREEN_HEIGHT) {
                obstacles.remove(ob);
                System.out.println("REMOVED AN OBSTACLE ONLY " + obstacles.size() + " LEFT");
            }
        }
        if (obstacles.size() == 0) {

        }
        if (obstacles.size() > 0) {

            if (obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
                int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));
                obstacles.add(new Missile(10, xStart, 0));
            }
        }


    }

    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
    }
}