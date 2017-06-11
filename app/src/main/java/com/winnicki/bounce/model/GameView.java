package com.winnicki.bounce.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.winnicki.bounce.R;

import java.util.ArrayList;

/**
 * Created by winnicki on 2017-06-02.
 */

public class GameView extends View implements View.OnTouchListener {

    private ArrayList<Ball> balls;
    private Paddle paddle;

    float canvasWidth;
    float canvasHeight;

    boolean ballsSet = false;
    boolean paddleSet = false;

    public GameView(Context context) {
        super(context);

        balls = new ArrayList<>();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 128, 128, true);
        balls.add(new Ball("Ball", 0, 0, 5f, 1, 1, 0, 0, 64, scaledBitmap));
        balls.add(new Ball("Ball2", 300f, 300f, 5f, 1, -1, 0, 0, 64, scaledBitmap));
        balls.add(new Ball("Ball3", 600f, 300f, 5f, -1, -1, 0, 0, 64, scaledBitmap));

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, 512, 128, true);
        paddle = new Paddle("Paddle", 0, 0, 512, 128, scaledBitmap);

        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        for(Ball ball : balls) {
            if(!ballsSet) {
                ball.setX(100f);
                ball.setY(300f);
                ballsSet = true;
            }
            canvas.drawBitmap(ball.getBitmap(), ball.getX() - ball.getRadius(), ball.getY() - ball.getRadius(), null);
        }

        if(!paddleSet) {
            paddle.setX(canvasWidth / 2 - paddle.getWidth() / 2);
            paddle.setY(canvasHeight - paddle.getHeight());
            paddleSet = true;
        }
        canvas.drawBitmap(paddle.getBitmap(), paddle.getX(), paddle.getY(), null);
    }

    public void play() {
        for(int i=0;i<balls.size();i++) {
            wallCollision(i);
            /*for(int j=0;j<balls.size();j++) {
                if(i != j) {
                    if (balls.get(i).colliding(balls.get(j))) {
                        reverseBallDirection(i);
                    }
                }
            }*/
            moveBall(i);
        }
    }
    private void wallCollision(int i) {
        if(balls.get(i).getX() <= 0 || balls.get(i).getX() >= canvasWidth) {
            balls.get(i).setDirectionX(balls.get(i).getDirectionX() * (-1));
        } else if(balls.get(i).getY() <= 0 || balls.get(i).colliding(paddle)) {
            balls.get(i).setDirectionY(balls.get(i).getDirectionY() * (-1));
        } else if(balls.get(i).getY() >= canvasHeight) {
            resetBall(i);
        }
    }

    /*private void reverseBallDirection(int i) {
        balls.get(i).setDirectionX(balls.get(i).getDirectionX() * (-1));
        balls.get(i).setDirectionY(balls.get(i).getDirectionY() * (-1));
    }*/

    private void moveBall(int i) {
        balls.get(i).setVelocityX(balls.get(i).getSpeed() * balls.get(i).getDirectionX());
        balls.get(i).setVelocityY(balls.get(i).getSpeed() * balls.get(i).getDirectionY());
        balls.get(i).move(balls.get(i).getVelocityX(), balls.get(i).getVelocityY());
    }

    private void resetBall(int i) {
        balls.get(i).setY(0f);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                paddle.setX(event.getX() - paddle.getWidth()/2);
                break;
            case MotionEvent.ACTION_DOWN:
                paddle.setX(event.getX() - paddle.getWidth()/2);
                break;
        }
        return true;
    }
}
