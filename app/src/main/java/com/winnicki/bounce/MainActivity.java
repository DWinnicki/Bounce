package com.winnicki.bounce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.winnicki.bounce.model.Animation;
import com.winnicki.bounce.model.GameView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout gameCanvas;
    Button btnStart;
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
        btnStart = (Button)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(this);

        gameView = new GameView(this);
        gameCanvas.addView(gameView);
        animation = new Animation(gameView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                animation.start();
                break;
        }
    }
}