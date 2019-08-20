package com.example.hackathonproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

import static com.example.hackathonproject.GameThread.canvas;

public class GameplayScene implements Scene {
    private Player player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private boolean gameOver = false;
    private long gameOverTime;
    private ArrayList<Background> backgrounds;
    // Game Over Screen
    private Rect r = new Rect();
    boolean downState = false;
    boolean upState = false;
    Point downPoint;
    double degree = 0;


    public GameplayScene() {
        player = new Player(new Rect(0, 0, 150, 150));
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager();
        player.addObserver(obstacleManager);
        //load the background data into the Background objects and
        // place them in our GameObject arraylist
        backgrounds = new ArrayList();

        backgrounds.add(new Background(
                Constants.CURRENT_CONTEXT,
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                "clouds", 0, 80, 50));

        backgrounds.add(new Background(
                Constants.CURRENT_CONTEXT,
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                "grass", 70, 110, 150));

    }

    public void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager();
    }


    @Override
    public void update() {
        if (!gameOver) {
            player.update(playerPoint);
            obstacleManager.update();

            if (obstacleManager.playerCollide(player)) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
            if (upState&&downState) {
                obstacleManager.flick(downPoint,degree);
            }

            // Update all the background positions
            for (Background bg : backgrounds) {
                bg.update(30);
            }
        }

    }

    @Override
    public void draw(Canvas canvas) {
//        canvas.drawColor(Color.WHITE);
        drawBackground(0);
        drawBackground(1);
        player.draw(canvas);
        obstacleManager.draw(canvas);

        if (gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(80);
            paint.setColor(Color.BLACK);
            drawCenterText(canvas, paint, "Game Over");
        }

    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;

    }

    @Override
    public void receiveTouch(MotionEvent event) {
        System.out.println("DOES THIS WORK?????????????????????");

        if (!downState && upState) {
            System.out.println("Somethings messed up");
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!downState) {
                    downPoint = new Point((int) event.getX(), (int) event.getY());
                    downState = true;
                }

                // After game is over for 2 seconds press down to reset the game
                if (gameOver && System.currentTimeMillis() - gameOverTime >= 2000) {
//                    reset();
//                    gameOver = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!upState) {
                    float diffX = downPoint.x - event.getX();
                    float diffY = downPoint.y - event.getY();
                    degree = Math.tan(diffY/diffX);
                    upState = true;
                } else {
                    upState = false;
                    downState = false;
                    degree = 0;
                }


        }
    }

    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

    private void drawBackground(int position) {
        Paint paint = new Paint();

        // Make a copy of the relevant background
//        backgrounds.add(new Background(   "clouds",  0, 80, 50));
//
//        backgrounds.add(new Background(
//                Constants.CURRENT_CONTEXT,
//                Constants.SCREEN_WIDTH,
//                Constants.SCREEN_HEIGHT,
//                "grass",  70, 110, 200));
        Background bg = backgrounds.get(position);


        // define what portion of images to capture and
        // what coordinates of screen to draw them at

        // For the regular bitmap
        Rect fromRect1 = new Rect(0, 0, bg.width - bg.xClip, bg.height);
        Rect toRect1 = new Rect(bg.xClip, bg.startY, bg.width, bg.endY);

        // For the reversed background
        Rect fromRect2 = new Rect(bg.width - bg.xClip, 0, bg.width, bg.height);
        Rect toRect2 = new Rect(0, bg.startY, bg.xClip, bg.endY);

        //draw the two background bitmaps
        if (!bg.reversedFirst) {
            canvas.drawBitmap(bg.bitmap, fromRect1, toRect1, paint);
            canvas.drawBitmap(bg.bitmapReversed, fromRect2, toRect2, paint);
        } else {
            canvas.drawBitmap(bg.bitmap, fromRect2, toRect2, paint);
            canvas.drawBitmap(bg.bitmapReversed, fromRect1, toRect1, paint);
        }

    }

}
