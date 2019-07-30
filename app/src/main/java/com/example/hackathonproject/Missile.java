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

public class Missile extends Obstacle {
    public static int width = 75;
    public static int height = 150;
    private int color = Color.BLACK;
    private static int playerPosX;

    private Animation moving;
    private Animation explosion;

    private AnimationManager aniManager;


    private int xPos; // of the middle of the missile
    private int yPos = 0; // of the top of the missile
    double xDistance;

    private Rect rect;


    public Missile(Point playerPos) {
        super(new Rect(0, 0, height, width), Color.DKGRAY);
        xPos = (int) (Math.random() * (Constants.SCREEN_WIDTH - Missile.width));  // here xpos is the left side of the missile
        rectangle = new Rect(xPos, yPos, xPos + width, yPos + height);
//        xPos += width/2; // now its the middle of the missile
        xDistance = playerPos.x - xPos;
        playerPosX = playerPos.x;
        BitmapFactory bf = new BitmapFactory();
        Bitmap movenot1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.missile_1);
        Bitmap movenot2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.missile_2);
        Bitmap movenot3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.missile_3);

        Bitmap move1 = RotateBitmap(movenot1,180);
        Bitmap move2 = RotateBitmap(movenot2,180);
        Bitmap move3 = RotateBitmap(movenot3,180);


        moving = new Animation(new Bitmap[]{move1,move2,move3}, .5f);

        aniManager = new AnimationManager(new Animation[]{moving});

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
        updateyPos();

        if (!b) {
            aniManager.playAnim(0);
            b = true;
        }
        aniManager.update();
//        xPos = rectangle.left + width/2;
    }

    public void updatexPos(double xDist) {
        xDist = xDist / 300;    // should calculate xDist every time to compensate for rounding
        rectangle.left += (xDist);
        rectangle.right += (xDist);
//        System.out.println("This is what the x direction is changing by" + xDist);
    }

    public void updateyPos() {
        rectangle.top += Constants.SCREEN_HEIGHT / 300;
        rectangle.bottom += Constants.SCREEN_HEIGHT / 300;
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
//        Paint paint = new Paint();
//        paint.setColor(color);
//        canvas.drawRect(rectangle, paint);
        aniManager.draw(canvas, rectangle);

    }
}
