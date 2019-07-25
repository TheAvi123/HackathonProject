package com.example.hackathonproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private Player player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;

    private GameThread thread;
    // will eventually need to make a list of everything on screen for game panel to draw

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
        player = new Player(new Rect(Constants.SCREEN_WIDTH/2 - 50, 10, Constants.SCREEN_WIDTH/2 + 50, 50));
        playerPoint = new Point(150, 150);
        obstacleManager = new ObstacleManager();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }

    // will handle all touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }

    public void update() {
        player.update(playerPoint);
        obstacleManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);
        obstacleManager.draw(canvas);
    }


}
