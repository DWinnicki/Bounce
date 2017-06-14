package com.winnicki.bounce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winnicki.bounce.model.Animation;
import com.winnicki.bounce.model.GameView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout gameCanvas;
    TextView tvWelcome;
    Button btnStart, btnPause, btnResume, btnRestart;

    Button buttons[] = {
            btnStart,
            btnPause,
            btnResume,
            btnRestart
    };

    int buttonIds[] = {
            R.id.btnStart,
            R.id.btnPause,
            R.id.btnResume,
            R.id.btnRestart
    };
    GameView gameView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        gameCanvas = (LinearLayout)findViewById(R.id.gameCanvas);
        tvWelcome = (TextView)findViewById(R.id.tvWelcome);

        for(int i=0;i<buttons.length; i++) {
            buttons[i] = (Button)findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
    }

    private void gameSetup() {
        gameView = new GameView(this);
        gameCanvas.addView(gameView);
        animation = new Animation(gameView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                gameSetup();
                tvWelcome.setVisibility(View.GONE);
                buttons[0].setVisibility(View.GONE);
                buttons[3].setVisibility(View.VISIBLE);
                buttons[1].setVisibility(View.VISIBLE);
                animation.start();
                break;
            case R.id.btnPause:
                buttons[1].setVisibility(View.GONE);
                buttons[2].setVisibility(View.VISIBLE);
                animation.stop();
                break;
            case R.id.btnResume:
                buttons[2].setVisibility(View.GONE);
                buttons[1].setVisibility(View.VISIBLE);
                animation.start();
                break;
            case R.id.btnRestart:
                recreate();
                break;
        }
    }
}