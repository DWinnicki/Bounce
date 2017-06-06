package com.winnicki.bounce.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import com.winnicki.bounce.R;

import java.util.ArrayList;

/**
 * Created by winnicki on 2017-06-02.
 */

public class GameView extends View {

    private ArrayList<Ball> balls;
    private Paddle paddle;

    float canvasWidth;
    float canvasHeight;

    public GameView(Context context) {
        super(context);

        balls = new ArrayList<>();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        balls.add(new Ball("Ball", 50f, 50f, 5f, 1, 1, 0, 0, 64f, bitmap));
        balls.add(new Ball("Ball2", 300f, 300f, 5f, 1, -1, 0, 0, 64f, bitmap));
        balls.add(new Ball("Ball3", 600f, 300f, 2f, 1, -1, 0, 0, 64f, bitmap));
        balls.add(new Ball("Ball4", 300f, 100f, 5f, -1, -1, 0, 0, 64f, bitmap));

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);
        paddle = new Paddle("Paddle", 300f, 300f, 32, 32, bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(Ball ball : balls) {
            canvas.drawBitmap(ball.getBitmap(), ball.getX() - ball.getBitmap().getWidth() / 2, ball.getY() - ball.getBitmap().getWidth() / 2, null);
        }
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
    }

    public void move() {
        for(int i=0;i<balls.size();i++) {
            reverseDirection(i);
            for(int j=0;j<balls.size();j++) {
                if(i != j) {
                    if (balls.get(i).colliding(balls.get(j))) {
                        reverseBallDirection(i);
                    }
                }
            }
            setVelocities(i);
            moveBalls(i);
        }
    }
    private void reverseDirection(int i) {
        if(balls.get(i).getX() <= 0) {
            balls.get(i).setDirectionX(balls.get(i).getDirectionX() * (-1));
        } else if(balls.get(i).getX() >= canvasWidth) {
            balls.get(i).setDirectionX(balls.get(i).getDirectionX() * (-1));
        } else if(balls.get(i).getY() <= 0) {
            balls.get(i).setDirectionY(balls.get(i).getDirectionY() * (-1));
        } else if(balls.get(i).getY() >= canvasHeight) {
            balls.get(i).setDirectionY(balls.get(i).getDirectionY() * (-1));
        }
    }

    private void reverseBallDirection(int i) {
        balls.get(i).setDirectionX(balls.get(i).getDirectionX() * (-1));
        balls.get(i).setDirectionY(balls.get(i).getDirectionY() * (-1));
        //balls.get(j).setDirectionX(balls.get(j).getDirectionX() * (-1));
        //balls.get(j).setDirectionY(balls.get(j).getDirectionY() * (-1));
    }

    private void setVelocities(int i) {
        balls.get(i).setVelocityX(balls.get(i).getSpeed() * balls.get(i).getDirectionX());
        balls.get(i).setVelocityY(balls.get(i).getSpeed() * balls.get(i).getDirectionY());
    }

    private void moveBalls(int i) {
        balls.get(i).move(balls.get(i).getVelocityX(), balls.get(i).getVelocityY());
    }
}
