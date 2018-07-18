package com.sil.it.view_rnd;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.sss);
        button = (Button) findViewById(R.id.click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ValueAnimator valueAnimator = ValueAnimator.ofInt(10, 120,20,50, 1000);
                valueAnimator.setDuration(10000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Log.d("TAG", "onAnimationUpdate: " + animation.getAnimatedValue());
                        Log.d("TAG", "onAnimationUpdateF: " + animation.getAnimatedFraction());
                        textView.setText("START : " + animation.getAnimatedValue());
                    }
                });
                valueAnimator.start();
            }
        });


    }
}
