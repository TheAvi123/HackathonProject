package com.example.hackathonproject;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(),this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread = new GameThread(getHolder(),this);

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while(true) { // seems wrong
            try {
                thread.setRunning(false);
                thread.join();

            } catch (Exception e) {e.printStackTrace();}
            retry = false;
        }

    }

    // will handle all touch events
    @Override
    public boolean onTouchEvent(MotionEvent event){
        return super.onTouchEvent(event);

    }

    public void update()  {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


}
