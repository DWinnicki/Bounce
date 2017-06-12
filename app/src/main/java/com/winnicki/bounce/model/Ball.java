package com.winnicki.bounce.model;

import android.graphics.Bitmap;

/**
 * Created by winnicki on 2017-06-02.
 */

public class Ball extends GameObject {
    private float radius;
    private Bitmap bitmap;
    private float speed;
    private int directionX;
    private int directionY;
    private float velocityX;
    private float velocityY;
    private boolean ballSet;
    private boolean bouncedOfPaddle;

    public Ball(String name, float radius, int directionX, int directionY, Bitmap bitmap) {
        super(name);
        this.radius = radius;
        this.bitmap = bitmap;
        this.speed = 1.0f;
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
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
        this.setX(this.getX() + incrementX);
        this.setY(this.getY() + incrementY);
    }

    public boolean hitTop(Paddle paddle) {
        return this.getX() + this.getRadius() >= paddle.getX()
                && this.getX() - this.getRadius() <= paddle.getX() + paddle.getWidth()
                && this.getY() + this.getRadius() >= paddle.getY()
                && this.getY() + this.getRadius() <= paddle.getY() + 5                                                                                                                                                                                                        ;
    }

    public boolean hitSide(Paddle paddle) {
        return (this.getY() + this.getRadius() >= paddle.getY()
                && this.getY() - this.getRadius() <= paddle.getY() + paddle.getHeight()
                && this.getX() + this.getRadius() >= paddle.getX()
                && this.getX() + this.getRadius() <= paddle.getX() + 5)
                || (this.getY() + this.getRadius() >= paddle.getY()
                && this.getY() - this.getRadius() <= paddle.getY() + paddle.getHeight()
                && this.getX() - this.getRadius() <= paddle.getX() + paddle.getWidth()
                && this.getX() - this.getRadius() >= paddle.getX() + paddle.getWidth() - 5);
    }

    public void resetSpeed() {
        this.speed = 1.0f;
    }

    public void incrementSpeed() {
        this.speed += 1.0f;
    }
}
