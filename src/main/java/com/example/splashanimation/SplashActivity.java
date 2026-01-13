package com.example.splashanimation; // CAMBIA por tu package

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splashanimation.R;

public class SplashActivity extends AppCompatActivity {

    private TextView topTitle, bottomTitle;
    private ImageView img1, img2, img3, img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        topTitle = findViewById(R.id.TextViewTopTitle);
        bottomTitle = findViewById(R.id.TextViewBottomTitle);

        //ASIGNA LOS IMAGEVIEW DEL XML POR ID

        img1 = findViewById(R.id.ImageView1);
        img2 = findViewById(R.id.ImageView2);
        img3 = findViewById(R.id.ImageView3);
        img4 = findViewById(R.id.ImageView4);

        topTitle.setAlpha(0f);
        bottomTitle.setAlpha(0f);

        startAnimation();
    }

    private void startAnimation() {

        ObjectAnimator fadeInTop =
                ObjectAnimator.ofFloat(topTitle, View.ALPHA, 0f, 1f);
        fadeInTop.setDuration(1000);

        //ROTACION

        ObjectAnimator r1 =
                ObjectAnimator.ofFloat(img1, View.ROTATION, 0f, 360f);
        ObjectAnimator r2 =
                ObjectAnimator.ofFloat(img2, View.ROTATION, 0f, -360f);
        ObjectAnimator r3 =
                ObjectAnimator.ofFloat(img3, View.ROTATION, 0f, 360f);
        ObjectAnimator r4 =
                ObjectAnimator.ofFloat(img4, View.ROTATION, 0f, -360f);

        r1.setDuration(1500);
        r2.setDuration(1500);
        r3.setDuration(1500);
        r4.setDuration(1500);

        AnimatorSet rotateImages = new AnimatorSet();
        rotateImages.playTogether(r1, r2, r3, r4);

        //FADE DEL TITULO

        ObjectAnimator fadeInBottom =
                ObjectAnimator.ofFloat(bottomTitle, View.ALPHA, 0f, 1f);
        fadeInBottom.setDuration(1000);

        AnimatorSet total = new AnimatorSet();
        total.playSequentially(fadeInTop, rotateImages, fadeInBottom);
        total.start();

        //TRANSICION AL MENU

        total.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
