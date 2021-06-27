package com.example.constraintanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    boolean show = false;
    private ConstraintLayout constraint;
    private ConstraintSet cs = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imageView);
        constraint = findViewById(R.id.constraint);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show) {
                    hideDesc();
                } else {
                    showDesc();
                }
            }
        });
    }

    protected void showDesc() {
        show = true;

        cs.clone(this, R.layout.activity_main_detail);

        Transition transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1000);

        TransitionManager.beginDelayedTransition(constraint, transition);
        cs.applyTo(constraint);
    }

    protected void hideDesc() {
        show = false;

        cs.clone(this, R.layout.activity_main);

        Transition transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1000);

        TransitionManager.beginDelayedTransition(constraint, transition);
        cs.applyTo(constraint);
    }
}