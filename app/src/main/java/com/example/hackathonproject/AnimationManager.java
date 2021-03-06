package com.example.hackathonproject;

import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimationManager {

    private Animation[] animations;
    private int animationIndex;

    public AnimationManager(Animation[] animations) {
        this.animations = animations;
        animationIndex = 0;

    }

    public void playAnim(int index) {
        for (int i = 0; i < animations.length; i++) {
            if (i == index && !animations[index].isPlaying()) {
                animations[i].play();
            } else {
                animations[i].stop();
            }
        }
        animationIndex = index;
    }

    public void draw(Canvas canvas, Rect rect) {
        if (animations[animationIndex].isPlaying()) {
            animations[animationIndex].draw(canvas, rect);
        }
    }

    public void update() {
        if (animations[animationIndex].isPlaying()) {
            animations[animationIndex].update();
        }
    }
}
