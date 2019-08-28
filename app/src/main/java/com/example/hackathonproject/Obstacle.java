package com.example.hackathonproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Obstacle implements Sprite {

    BitmapFactory bf = new BitmapFactory();

    Bitmap movenot1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.missile_1);
    Bitmap movenot2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.missile_2);
    Bitmap movenot3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.missile_4);


    protected Rect rectangle;
    private int color;

    public Obstacle(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public boolean collidingWithPlayer(Player player) {
        return Rect.intersects(rectangle,player.getPlayerRectangle());
    }

    public boolean collideWithUser(Point point){
        return rectangle.contains(point.x,point.y);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);

    }

    @Override
    public void update() {}
    public void update(int elapsedTime) {}
    public void remove(){
        System.out.println("USING WRONG ONE");
    }
    public void flicked(double degree,Point point){}

}

