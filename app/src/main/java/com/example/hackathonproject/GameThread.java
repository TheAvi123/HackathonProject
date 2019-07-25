package com.example.hackathonproject;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class GameThread extends Thread {

    public static final int MAX_FPS = 30;
    private double averageFPS;

    private GamePanel gamePanel;
    private SurfaceHolder surfaceHolder;
    private boolean running;
    public  static Canvas canvas;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)  {
        super();
        this.gamePanel = gamePanel;
        this.surfaceHolder= surfaceHolder;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                if (canvas == null) {
                    System.out.println("CANVAS IS NULLLL");
                }
                synchronized (surfaceHolder) {
                    // Moves the Player
                    this.gamePanel.update();
                    // Relocates the player on the canvas
                    this.gamePanel.draw(canvas);
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("CAUGHT EXCEPTION IN RUNNING");
            }
            finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) /1000000;
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println("THIS IS THE FPS " + averageFPS + " I WANNA MAKE THIS STAND OUT");
            }
        }
    }



}
