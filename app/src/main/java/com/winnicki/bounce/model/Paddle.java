package com.winnicki.bounce.model;

import android.graphics.Bitmap;

public class Paddle extends GameObject {
    private float width;
    private float height;

    public Paddle(String name, Bitmap bitmap, float width, float height) {
        super(name, bitmap);
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getCenter() {
        return getWidth()/2;
    }

    public float getRightSide() {
        return this.getX() + getWidth();
    }

    public float getLeftSide() {
        return this.getX();
    }

    public float getTop() {
        return getY();
    }

    public float getBottom() {
        return getY() + getHeight();
    }
}
