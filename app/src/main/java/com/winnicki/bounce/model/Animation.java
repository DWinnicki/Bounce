package com.winnicki.bounce.model;

import android.util.Log;

/**
 * Created by winnicki on 2017-06-02.
 */

public class Animation implements Runnable {

    private GameView gameView;
    private Thread thread;

    public Animation(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void run() {
        while (thread != null) {
            gameView.move();
            gameView.postInvalidate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Log.d("Animation", e.getMessage());
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