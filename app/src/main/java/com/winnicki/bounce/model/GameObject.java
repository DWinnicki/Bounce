package com.winnicki.bounce.model;

/**
 * Created by winnicki on 2017-06-02.
 */

public class GameObject {
    private String name;
    private float x;
    private float y;

    public GameObject(String name, float x, float y) {
        this.name = name;
        this.x = x;
        this.y = y;
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
