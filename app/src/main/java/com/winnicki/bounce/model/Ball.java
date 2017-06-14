package com.winnicki.bounce.model;

import android.graphics.Bitmap;

public class Ball extends GameObject {
    private float radius;
    private float speed;
    private int directionX;
    private int directionY;
    private float velocityX;
    private float velocityY;
    private boolean ballSet;
    private boolean bouncedOfPaddle;

    public Ball(String name, Bitmap bitmap, float radius, int directionX, int directionY) {
        super(name, bitmap);
        this.radius = radius;
        this.directionX = directionX;
        this.directionY = directionY;
        this.velocityX = 0.0f;
        this.velocityY = 0.0f;
        this.ballSet = false;
        this.bouncedOfPaddle = false;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isBallSet() {
        return ballSet;
    }

    public void setBallSet(boolean ballSet) {
        this.ballSet = ballSet;
    }

    public boolean isBouncedOfPaddle() {
        return bouncedOfPaddle;
    }

    public void setBouncedOfPaddle(boolean bouncedOfPaddle) {
        this.bouncedOfPaddle = bouncedOfPaddle;
    }

    public void move(float incrementX, float incrementY) {
        setX(getX() + incrementX);
        setY(getY() + incrementY);
    }

    public boolean hitTop(Paddle paddle) {
        return getRightSide() >= paddle.getLeftSide()
                && getLeftSide() <= paddle.getRightSide()
                && getBottom() >= paddle.getTop()
                && getBottom() <= paddle.getTop() + 5                                                                                                                                                                                                        ;
    }

    public boolean hitLeftSide(Paddle paddle) {
        return getBottom() >= paddle.getTop()
                && getTop() <= paddle.getBottom()
                && getRightSide() >= paddle.getLeftSide()
                && getRightSide() <= paddle.getLeftSide() + 5;
    }

    public boolean hitRightSide(Paddle paddle) {
        return getBottom() >= paddle.getTop()
                && getTop() <= paddle.getBottom()
                && getLeftSide() <= paddle.getRightSide()
                && getLeftSide() >= paddle.getRightSide() - 5;
    }

    public float getRightSide() {
        return getX() + getRadius();
    }

    public float getLeftSide() {
        return getX() - getRadius();
    }

    public float getBottom() {
        return getY() + getRadius();
    }

    public float getTop() {
        return getY() - getRadius();
    }

    public void reset() {
        setY(getRadius());
        setSpeed(2.0f);
    }

    public void incrementSpeed() {
        setSpeed(getSpeed() + 1.0f);
    }
}
