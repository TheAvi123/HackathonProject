package com.example.hackathonproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Observable;

public class Player extends Observable implements Sprite  {

    private Rect rect;
    private int color = Color.BLUE;

    private Animation moving;
    private Animation death;


    private AnimationManager aniManager;

    public Player(Rect rectangle) {
        this.rect = rectangle;

        BitmapFactory bf = new BitmapFactory();
        Bitmap move1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.run_1);
        Bitmap move2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.run_2);
        Bitmap move3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.run_3);
        Bitmap move4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.run_4);
        Bitmap move5 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.run_5);
        Bitmap move6 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.run_6);
        Bitmap move7 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.run_7);
        Bitmap move8 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.run_8);

        moving = new Animation(new Bitmap[]{move1,move2,move3,move4,move5,move6,move7,move8},.7f);

        aniManager = new AnimationManager(new Animation[]{moving});
    }


    public Rect getPlayerRectangle() {
        return rect;
    }

    @Override
    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setColor(color);
//        canvas.drawRect(rect, paint);
        aniManager.draw(canvas,rect);
    }

    @Override
    public void update() {  //Regular update method
        aniManager.update();


    }
    boolean b = false;

    public void update(Point point) {   //Update method to move the player to a new point
        setChanged();
        notifyObservers(point);
//        point.x++;
        rect.set(point.x - rect.width() / 2, point.y - rect.height() / 2,
                point.x + rect.width() / 2, point.y + rect.height() / 2);

        if (!b) {
            aniManager.playAnim(0);
            b = true;
        }
        aniManager.update();
    }
}
