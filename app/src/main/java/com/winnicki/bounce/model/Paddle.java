package com.winnicki.bounce.model;

import android.graphics.Bitmap;

/**
 * Created by winnicki on 2017-06-02.
 */

public class Paddle extends GameObject {
    private float width;
    private float height;
    private  Bitmap bitmap;

    public Paddle(String name, float width, float height, Bitmap bitmap) {
        super(name);
        this.width = width;
        this.height = height;
        this.bitmap = bitmap;
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
