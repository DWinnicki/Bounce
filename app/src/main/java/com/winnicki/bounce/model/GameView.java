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
        balls.add(new Ball("Ball", scaledBitmap, scaledBitmap.getWidth()/2, 1, 1));
        balls.add(new Ball("Ball2", scaledBitmap, scaledBitmap.getWidth()/2, 1, -1));
        balls.add(new Ball("Ball3", scaledBitmap, scaledBitmap.getWidth()/2, -1, -1));
        balls.add(new Ball("Ball4", scaledBitmap, scaledBitmap.getWidth()/2, -1, 1));

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, 512, 128, true);
        paddle = new Paddle("Paddle", scaledBitmap, scaledBitmap.getWidth(), scaledBitmap.getHeight());

        score = new Score();

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(64);

        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(getContext().getString(R.string.caught_colon) + score.getCaught(), 10, 70, paint);
        canvas.drawText(getContext().getString(R.string.missed_colon) + score.getMissed(), 10, 140, paint);
        canvas.drawText(getContext().getString(R.string.score_colon) + score.getScore(), 10, 210, paint);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        for(Ball ball : balls) {
            if(!ball.isBallSet()) {
                float maxX = canvasWidth - ball.getRadius()*2;
                float minX = ball.getRadius()*2;
                float maxY = canvasHeight/2;
                float minY = ball.getRadius()*2;

                ball.setX((int)(Math.random() * (maxX - minX)) + minX);
                ball.setY((int)(Math.random() * (maxY - minY)) + minY);
                ball.setSpeed(random.nextInt(5 - 2) + 2);
                ball.setBallSet(true);
            }
            canvas.drawBitmap(ball.getBitmap(), ball.getX() - ball.getRadius(), ball.getY() - ball.getRadius(), null);
        }

        if(!paddleSet) {
            paddle.setX(canvasWidth/2 - paddle.getCenter());
            paddle.setY(canvasHeight - paddle.getHeight()*2);
            paddleSet = true;
        }
        canvas.drawBitmap(paddle.getBitmap(), paddle.getX(), paddle.getY(), null);
    }

    public void play() {
        for(int i=0;i<balls.size();i++) {
            Ball ball = balls.get(i);
            wallCollision(ball);
            paddleCollision(ball);
            moveBall(ball);
        }
    }

    private void wallCollision(Ball ball) {
        if(ball.getLeftSide() <= 0 || ball.getRightSide() >= canvasWidth) {
            ball.setBouncedOfPaddle(false);
            ball.setDirectionX(ball.getDirectionX() * (-1));
        } else if(ball.getTop() <= 0) {
            ball.setBouncedOfPaddle(false);
            ball.setDirectionY(ball.getDirectionY() * (-1));
        } else if(ball.getTop() >= canvasHeight) {
            ball.setBouncedOfPaddle(false);
            resetBall(ball);
        }
    }

    private void paddleCollision(Ball ball) {
    if(ball.hitTop(paddle) && !ball.isBouncedOfPaddle()) {
        score.incrementCaught();
        score.incrementScore(ball);
        ball.incrementSpeed();
        ball.setDirectionY(ball.getDirectionY() * (-1));
        ball.setBouncedOfPaddle(true);
    } else if(ball.hitLeftSide(paddle) && !ball.isBouncedOfPaddle()) {
        ball.setDirectionX(-1);
        ball.incrementSpeed();
        ball.setBouncedOfPaddle(true);
    } else if(ball.hitRightSide(paddle) && !ball.isBouncedOfPaddle()) {
        ball.setDirectionX(1);
        ball.incrementSpeed();
        ball.setBouncedOfPaddle(true);
    }
    }

    private void moveBall(Ball ball) {
        ball.setVelocityX(ball.getSpeed() * ball.getDirectionX());
        ball.setVelocityY(ball.getSpeed() * ball.getDirectionY());
        ball.move(ball.getVelocityX(), ball.getVelocityY());
    }

    private void resetBall(Ball ball) {
        ball.reset();
        score.incrementMissed();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                paddle.setX(event.getX() - paddle.getCenter());
                break;
            case MotionEvent.ACTION_DOWN:
                paddle.setX(event.getX() - paddle.getCenter());
                break;
        }
        return true;
    }
}
