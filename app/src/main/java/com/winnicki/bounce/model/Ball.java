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

    public Ball(String name, float x, float y, float speed, int directionX, int directionY, float velocityX, float velocityY, float radius, Bitmap bitmap) {
        super(name, x, y);
        this.radius = radius;
        this.bitmap = bitmap;
        this.speed = speed;
        this.directionX = directionX;
        this.directionY = directionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
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

    public void move(float incrementX, float incrementY) {
        this.setX(this.getX() + incrementX);
        this.setY(this.getY() + incrementY);
    }

    public boolean colliding(Ball ball)
    {
        float distanceX = this.getX() - ball.getX();
        float distanceY = this.getY() - ball.getY();

        float sumRadius = getRadius() + ball.getRadius();
        float radiusSquare = sumRadius * sumRadius;

        float distanceSquare = (distanceX * distanceX) + (distanceY * distanceY);

        return distanceSquare <= radiusSquare;
    }
}
