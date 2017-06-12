package com.winnicki.bounce.model;

/**
 * Created by winnicki on 2017-06-02.
 */

public class GameObject {
    private String name;
    private float x;
    private float y;

    public GameObject(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
