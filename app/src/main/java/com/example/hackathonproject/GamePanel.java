package com.example.hackathonproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import static com.example.hackathonproject.GameThread.canvas;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private SceneManager manager;
    ArrayList<Background> backgrounds;
    private GestureDetector detector;
    private MyGestureDetector myGestureDetector;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
        Constants.CURRENT_CONTEXT = context;

        manager = new SceneManager();

        MyGestureDetector myGestureDetector= new MyGestureDetector();
        detector = new GestureDetector(Constants.CURRENT_CONTEXT,myGestureDetector);
        detector.setOnDoubleTapListener(myGestureDetector);



    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread = new GameThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        // Method is not used
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
        manager.reciveTouch(event);
//        detector.onTouchEvent(event);

        return true;
    }



    public void update() {
        manager.update();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        manager.draw(canvas);

    }

}
