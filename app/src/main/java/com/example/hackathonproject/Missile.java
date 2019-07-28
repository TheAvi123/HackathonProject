package com.example.hackathonproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Missile extends Obstacle {
    public static int width = 75;
    public static int height = 150;
    private int color = Color.BLACK;

    private int xPos; // of the left side of the missile
    private int yPos = 0; // of the top of the missile
    double xDistance;

    public Missile(Point playerPos) {
        super(new Rect(height,width,height,width), Color.DKGRAY);
        xPos = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));
        rectangle = new Rect(xPos ,yPos,xPos + width, yPos + height);
        xDistance = playerPos.x - xPos;//
    }

    public void update(int elapsedTime) {
        updatexPos(xDistance);
        updateyPos();
    }

    public void updatexPos(double xDist) {
        xDist = xDist/300;
        rectangle.left += (xDist);
        rectangle.right += (xDist);
        System.out.println("This is what the x direction is changing by" + xDist);
    }

    public void updateyPos() {
        rectangle.top += (Constants.SCREEN_HEIGHT/300);
        rectangle.bottom += (Constants.SCREEN_HEIGHT/300);
    }

    public void update(Point point) {   //Update method to move the obstacle to a new point
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2,
                     point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
    }

    @Override
    public void draw(Canvas canvas) { // this stuff should be abstract
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }
}
