package com.example.hackathonproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public abstract class Obstacle implements Sprite {


    protected Rect rectangle;
    private int color = Color.BLACK;

    public Obstacle(int RectHeight) {
        this.rectangle = rectangle;

        }

    public Rect getRectangle() {
        return rectangle;
    }

    public boolean collidingWithPlayer(Player player) {
        return (rectangle.contains(player.GetPlayerRectangle().left, player.GetPlayerRectangle().top)
                || rectangle.contains(player.GetPlayerRectangle().right, player.GetPlayerRectangle().top)
                || rectangle.contains(player.GetPlayerRectangle().left, player.GetPlayerRectangle().bottom)
                || rectangle.contains(player.GetPlayerRectangle().right, player.GetPlayerRectangle().bottom));
    }

    @Override
    abstract public void draw(Canvas canvas);

    abstract public void update(int elapsedTime);
// this stuff is weird
    @Override
    public void update(){}

    abstract public void update(Point point);   //Update method to move the obstacle to a new point
}
