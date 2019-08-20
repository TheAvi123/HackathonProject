package com.example.hackathonproject;

import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.Observable;

public class MyGestureDetector implements android.view.GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float v, float v1) {
        float degree = 0;
        float diffX = downEvent.getX() - moveEvent.getX();
        float diffY = downEvent.getY() - moveEvent.getY();
        degree = (float) Math.tan(diffY/diffX);

        System.out.println("GESTURECONTROL - degrees: " + degree);
        float[] points = {downEvent.getX(),downEvent.getY(),degree};

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}
