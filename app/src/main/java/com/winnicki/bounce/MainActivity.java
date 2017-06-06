package com.winnicki.bounce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.winnicki.bounce.model.Animation;
import com.winnicki.bounce.model.GameView;

public class MainActivity extends AppCompatActivity {
    LinearLayout gameCanvas;
    GameView gameView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        gameCanvas = (LinearLayout) findViewById(R.id.gameCanvas);
        gameView = new GameView(this);
        gameCanvas.addView(gameView);
        animation = new Animation(gameView);

        animation.start();
    }
}