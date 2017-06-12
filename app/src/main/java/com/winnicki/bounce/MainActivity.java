package com.winnicki.bounce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.winnicki.bounce.model.Animation;
import com.winnicki.bounce.model.GameView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout gameCanvas;
    Button btnStart, btnStop;
    GameView gameView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        gameCanvas = (RelativeLayout) findViewById(R.id.gameCanvas);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        gameView = new GameView(this);
        gameCanvas.addView(gameView);
        animation = new Animation(gameView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                btnStart.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);
                animation.start();
                break;
            case R.id.btnStop:
                btnStop.setVisibility(View.GONE);
                btnStart.setVisibility(View.VISIBLE);
                animation.stop();
                break;
        }
    }
}