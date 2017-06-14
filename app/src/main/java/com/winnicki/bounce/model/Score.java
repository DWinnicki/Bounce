package com.winnicki.bounce.model;

public class Score {
    private int caught;
    private int missed;
    private int score;

    public Score() {
        this.caught = 0;
        this.missed = 0;
        this.score = 0;
    }

    public int getCaught() {
        return caught;
    }

    public void setCaught(int caught) {
        this.caught = caught;
    }

    public int getMissed() {
        return missed;
    }

    public void setMissed(int missed) {
        this.missed = missed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementCaught() {
        this.caught++;
    }

    public void incrementMissed() {
        this.missed++;
    }

    public void incrementScore(Ball ball) {
        this.score += (ball.getSpeed()*10);
    }
}
