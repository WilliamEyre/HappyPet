package com.example.laptop.petshappy.svg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.example.laptop.petshappy.R;
import com.example.laptop.petshappy.guide.GuideActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Laptop on 2018/1/5.
 */
public class WelcomeActivity extends AppCompatActivity {

    Handler handler = new Handler();
    @InjectView(R.id.pathView)
    PathView pathView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);

        ButterKnife.inject(this);

        initView();
    }

    private void initView() {
        pathView.getPathAnimator()
                .delay(1000)
                .duration(3000)
                .listenerStart(new PathView.AnimatorBuilder.ListenerStart() {
                    @Override
                    public void onAnimationStart() {

                    }
                })
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {

                    private boolean isFirstIn;

                    @Override
                    public void onAnimationEnd() {
                        pathView.setFillAfter(true);
                        SharedPreferences sharedPreferences = getSharedPreferences("is_first_in_data", MODE_PRIVATE);
                        isFirstIn = sharedPreferences.getBoolean("isFirstIn", true);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (isFirstIn) {
                                    startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                                    SharedPreferences pref = getSharedPreferences("is_first_in_data", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putBoolean("isFirstIn", false);
                                    editor.commit();
                                } else {
                                    startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                                    finish();
                                }
                            }
                        }, 2000);
                    }
                })
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();
    }
}
