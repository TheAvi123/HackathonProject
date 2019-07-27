package com.example.hackathonproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.style.UpdateAppearance;

import java.util.Observable;
import java.util.Observer;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Missile extends Obstacle {
    public static int width = 75;
    public static int height = 150;
    private float speed = Constants.SCREEN_HEIGHT / 1000.0f;
    private int color = Color.BLACK;
    protected Point playersPos;

    private int xPos; // of the left side of the missile
    private int yPos; // of the top of the missile
    private int slope = 1;
    private float rotation;


    private int startX;

    public Missile(Point playerPos) {
        super(new Rect(height,width,height,width), Color.DKGRAY);
        startX = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));
        xPos = startX;
//        yPos = -Constants.SCREEN_HEIGHT / 5;
        yPos = 0;
        //l,t,r,b
        rectangle = new Rect(xPos ,yPos,xPos + width, yPos + height);
//        slope = slopeToPlayer();
        rotation = (float) Math.toDegrees(Math.atan2(playerPos.y - yPos, playerPos.x - xPos)) + 90;
        System.out.println(rotation + "THIS IS THE ROTATION");
    }

//    private int distanceFromPlayer() {
//        int y2 = playersPos.y;
//        int x2 = playersPos.x;
//
//        return (int) (Math.sqrt((y2 - yPos) * (y2 - yPos) + (x2 - xPos) * (x2 - xPos)));
//    }

//    private int slopeToPlayer() {
//        int y2 = playersPos.y;
//        int x2 = playersPos.x;
//        return (y2-yPos)/(x2-xPos);
//    }

    public void update(int elapsedTime) {
        double xDirection = sin((float) Math.toRadians(getRotation()));
        double yDirection = cos((float) Math.toRadians(getRotation()));

        updatexPos(xDirection);
        updateyPos(yDirection);
    }

    public void updatexPos(double xDir) {
        rectangle.left += (xDir * speed);
        rectangle.right += (xDir * speed);
    }

    public void updateyPos(double yDir) {
        rectangle.top += (yDir * -speed);
        rectangle.bottom += (yDir * -speed);
        System.out.println((yDir * -speed) + "THIS IS WHAT THE YPOS IS CHANGING BY");
    }

    public void update(Point point) {   //Update method to move the obstacle to a new point
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2,
                point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
    }

    @Override
    public void draw(Canvas canvas) { // this stuff should be abstract
        Paint paint = new Paint();
        paint.setColor(color);
//        canvas.save();
//        canvas.rotate(rotation);
        canvas.drawRect(rectangle, paint);
//        canvas.restore();
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getSlope() {
        return slope;
    }

    public float getRotation() {
        return rotation;
    }

}
