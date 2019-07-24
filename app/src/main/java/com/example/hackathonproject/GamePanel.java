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

    private GameThread thread;
    // will eventually need to make a list of everything on screen for game panel to draw

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

        setFocusable(true);
        player = new Player(new Rect(100, 100, 200, 200), Color.RED, 10,20);
        playerPoint = new Point(150, 150);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
//        thread = new GameThread(getHolder(), this);
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
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x=  (int)event.getX();
            int y = (int)event.getY();

            int movingVectorX =x-  this.player.getX();
            int movingVectorY =y-  this.player.getY() ;

            return true;
        }
        return false;
    }

    public void update() {
        player.update(playerPoint);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        canvas.drawColor(Color.WHITE);
        player.draw(canvas);
    }


}
