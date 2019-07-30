package com.example.hackathonproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;

import java.util.ArrayList;

public class Animation {
    private Bitmap[] frames;
    private int frameIndex;
    private static float ratio;

    private boolean isPlaying = false;

    private float frameTime;
    private long lastFrame;

    public Animation(Bitmap[] frames, float animTime) {
        ratio = (float) (frames[frameIndex].getWidth())/frames[frameIndex].getHeight();

        this.frames = frames;
        frameIndex = 0;
        frameTime = animTime/frames.length;
        lastFrame = System.currentTimeMillis();
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void play() {
        isPlaying = true;
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
    }

    public void stop() {
        isPlaying = false;
    }



    public void draw(Canvas canvas, Rect destination) {
        if (!isPlaying) {
            return;
        }
        scaleRect(destination);


        canvas.drawBitmap(frames[frameIndex], null, destination,new Paint());
    }

    public void update() {
        if (!isPlaying) {
            return;
        }
        if (System.currentTimeMillis() - lastFrame > frameTime*1000) {
            frameIndex++;
//            System.out.println("FRAMEINDEX++ DFSDFHSDJ");
            frameIndex = frameIndex >= frames.length ? 0 : frameIndex;
            lastFrame = System.currentTimeMillis();
        }
    }

    private void scaleRect(Rect rect) {
        if(rect.width() > rect.height()) {
            rect.left = (int) (rect.right - (rect.height() * ratio));
        } else {
            rect.top = (int) (rect.bottom - (rect.width() * (1/ratio)));

        }
    }

}
