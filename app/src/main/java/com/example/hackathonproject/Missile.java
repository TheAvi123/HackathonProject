package com.example.hackathonproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.Random;

public class Missile extends Obstacle {
    public static int width = 75;
    public static int height = 150;
    private int color = Color.BLACK;
    private static int playerPosX;
    private static int playerPosY;

    private Animation moving;
    private Animation explosion;

    private AnimationManager aniManager;


    private float rand;
    private int xPos; // of the middle of the missile
    private int yPos = 0; // of the top of the missile
    double xDistance;
    double yDistance;

    private Rect rect;
    Bitmap move1;
    Bitmap move2;
    Bitmap move3;


    public Missile(Point playerPos) {
        super(new Rect(0, 0, height, width), Color.DKGRAY);


        initializePositions(playerPos);

        renderAnimation(playerPos);

    }

    private void initializePositions(Point playerPos) {
        Random random = new Random();
        int rand= random.nextInt(3);

        xPos = getRandXPos(rand);
        yPos = getRandYPos(rand);

        rectangle = new Rect(xPos, yPos, xPos + width, yPos + height);
        xDistance = playerPos.x - xPos;
        yDistance = playerPos.y - yPos;
        playerPosX = playerPos.x;
        playerPosY = playerPos.y;
    }

    private void renderAnimation(Point playerPos) {
        int degrees = (int) getAngle(playerPos) + 90;

        move1 = RotateBitmap(movenot1,degrees);
        move2 = RotateBitmap(movenot2,degrees);
        move3 = RotateBitmap(movenot3,degrees);

        moving = new Animation(new Bitmap[]{move1,move2,move3}, .5f);
        movenot1.recycle();
        movenot2.recycle();
        movenot3.recycle();

        aniManager = new AnimationManager(new Animation[]{moving});
    }

    private int getRandXPos(int rand) {
        int xPos;
        if (rand == 0) {
            xPos = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));  // here xpos is the left side of the missile
        } else {
            if (rand == 1) {
                xPos = 0;
            } else {
                xPos = Constants.SCREEN_WIDTH;
            }
        }
        return xPos;
    }

    private int getRandYPos(int rand){
        int yPos;
        if (rand == 0) {
            yPos = 0;
        } else {
//            rand = (float) Math.random();
            if (rand == 1) {
                yPos = (int) (Math.random() * (Constants.SCREEN_HEIGHT - Missile.height));  // here xpos is the left side of the missile
            } else {
                yPos = (int) (Math.random() * (Constants.SCREEN_HEIGHT - Missile.height));  // here xpos is the left side of the missile
            }
        }

        return yPos;
    }

    public float getAngle(Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - yPos, target.x - xPos));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    public void remove() {
        move1.recycle();
        move2.recycle();
        move3.recycle();
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


    boolean b = false;
    public void update(int elapsedTime) {
//        xDistance = playerPosX - xPos;
        updatexPos(xDistance);
        updateyPos(yDistance);

        if (!b) {
            aniManager.playAnim(0);
            b = true;
        }
        aniManager.update();
    }

    public void updatexPos(double xDist) {
        xDist = xDist / 300;    // should calculate xDist every time to compensate for rounding
        rectangle.left += (xDist) * 2;
        rectangle.right += (xDist) * 2;
    }

    public void updateyPos(double yDist) {
        yDist = yDist / 300;
        rectangle.top += (yDist) * 3;
        rectangle.bottom += (yDist) * 3;
    }

    public void update(Point point) {   //Update method to move the obstacle to a new point
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2,
                point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);

        if (!b) {
            aniManager.playAnim(0);
            b = true;
        }
        aniManager.update();
    }

    @Override
    public void draw(Canvas canvas) { // this stuff should be abstract
        aniManager.draw(canvas, rectangle);
    }

    @Override
    public void flicked(double degree) {
        // implement this method

    }
}

