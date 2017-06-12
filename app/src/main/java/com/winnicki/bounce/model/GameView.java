package com.winnicki.bounce.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.winnicki.bounce.MainActivity;
import com.winnicki.bounce.R;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View implements View.OnTouchListener {

    private ArrayList<Ball> balls;
    private Paddle paddle;
    private Score score;

    float canvasWidth;
    float canvasHeight;

    boolean paddleSet = false;

    private Random random = new Random();

    private Paint paint;

    public GameView(Context context) {
        super(context);

        balls = new ArrayList<>();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 128, 128, true);
        balls.add(new Ball("Ball", scaledBitmap.getWidth()/2, 1, 1, scaledBitmap));
        balls.add(new Ball("Ball2", scaledBitmap.getWidth()/2, 1, -1, scaledBitmap));
        balls.add(new Ball("Ball3", scaledBitmap.getWidth()/2, -1, -1, scaledBitmap));
        balls.add(new Ball("Ball4", scaledBitmap.getWidth()/2, -1, 1, scaledBitmap));

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, 512, 128, true);
        paddle = new Paddle("Paddle", 512, 128, scaledBitmap);

        score = new Score();

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(64);

        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("Caught: " + score.getCaught(), 10, 70, paint);
        canvas.drawText("Missed: " + score.getMissed(), 10, 140, paint);
        canvas.drawText("Score: " + score.getScore(), 10, 210, paint);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        for(Ball ball : balls) {
            if(!ball.isBallSet()) {
                ball.setX(random.nextInt((int)canvasWidth - 1) + 1);
                ball.setY(300f);
                ball.setBallSet(true);
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
            moveBall(i);
        }
    }
    private void wallCollision(int i) {
        if(balls.get(i).getX() <= 0 || balls.get(i).getX() >= canvasWidth) {
            balls.get(i).setBouncedOfPaddle(false);
            balls.get(i).setDirectionX(balls.get(i).getDirectionX() * (-1));
        } else if(balls.get(i).getY() <= 0) {
            balls.get(i).setBouncedOfPaddle(false);
            balls.get(i).setDirectionY(balls.get(i).getDirectionY() * (-1));
        } else if(balls.get(i).hitTop(paddle) && !balls.get(i).isBouncedOfPaddle()) {
            score.incrementCaught();
            score.incrementScore(balls.get(i));
            balls.get(i).incrementSpeed();
            balls.get(i).setDirectionY(balls.get(i).getDirectionY() * (-1));
            balls.get(i).setBouncedOfPaddle(true);
        } else if(balls.get(i).hitSide(paddle) && !balls.get(i).isBouncedOfPaddle()) {
            balls.get(i).setDirectionX(balls.get(i).getDirectionX() * (-1));
            balls.get(i).setBouncedOfPaddle(true);
        } else if(balls.get(i).getY() >= canvasHeight) {
            balls.get(i).setBouncedOfPaddle(false);
            resetBall(i);
        }
    }

    private void moveBall(int i) {
        balls.get(i).setVelocityX(balls.get(i).getSpeed() * balls.get(i).getDirectionX());
        balls.get(i).setVelocityY(balls.get(i).getSpeed() * balls.get(i).getDirectionY());
        balls.get(i).move(balls.get(i).getVelocityX(), balls.get(i).getVelocityY());
    }

    private void resetBall(int i) {
        balls.get(i).setY(0f);
        balls.get(i).resetSpeed();
        score.incrementMissed();
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
