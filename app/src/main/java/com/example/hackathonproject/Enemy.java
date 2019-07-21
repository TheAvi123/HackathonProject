package com.example.hackathonproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public abstract class Enemy implements GameObject {

    private Rect rectangle;
    private int color;

    public Enemy(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
    }

    public boolean collidingWithPlayer(Player player) {
        return rectangle.contains(player.GetPlayerRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {  //Regular update method
    }

    public void update(Point point) {   //Update method to move the player to a new point
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2,
                point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
