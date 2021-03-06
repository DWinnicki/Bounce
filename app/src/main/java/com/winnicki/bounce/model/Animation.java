package com.winnicki.bounce.model;

import android.util.Log;

public class Animation implements Runnable {

    private GameView gameView;
    private Thread thread;

    public Animation(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void run() {
        while (thread != null) {
            gameView.play();
            gameView.postInvalidate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Log.d("ANIMATION", e.getMessage());
            }
        }
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        thread = null;
    }
}