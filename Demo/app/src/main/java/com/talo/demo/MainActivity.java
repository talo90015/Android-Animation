package com.talo.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity{

    private LinearLayout layout;
    private TextView textView;
    private Button btnRotate, btnTrans, btnDrop, btnScale, btnShift, btnChangeColo;
    private float y, yend;
    private boolean mIsFallingFirst = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.line1);
        textView = findViewById(R.id.text);
        btnRotate = findViewById(R.id.btnRotate);
        btnTrans = findViewById(R.id.btnTrans);
        btnDrop = findViewById(R.id.btnDrop);
        btnScale = findViewById(R.id.btnScale);
        btnShift = findViewById(R.id.btnShift);
        btnChangeColo = findViewById(R.id.btnChangeColor);

        btnRotate.setOnClickListener(btnRotateClick);
        btnTrans.setOnClickListener(btnTransClick);
        btnDrop.setOnClickListener(btnDropClick);
        btnScale.setOnClickListener(btnScaleClick);
        btnShift.setOnClickListener(btnShiftClick);
        btnChangeColo.setOnClickListener(btnChangeColoClick);



    }
    private View.OnClickListener btnRotateClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "rotation", 0, 360);
            animator.setDuration(3000);
            animator.setRepeatCount(1);
            animator.setRepeatMode(ObjectAnimator.REVERSE);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.start();
        }
    };
    private View.OnClickListener btnTransClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "alpha", 1, 0);
            animator.setDuration(2500);
            animator.setRepeatCount(1);
            animator.setRepeatMode(ObjectAnimator.REVERSE);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();

        }
    };
    private View.OnClickListener btnDropClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mIsFallingFirst){
                y = textView.getY();
                yend = layout.getHeight() - textView.getHeight();
                mIsFallingFirst = false;
            }
            ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "y", y, yend);
            animator.setDuration(2000);
            animator.setRepeatCount(ObjectAnimator.INFINITE);
            animator.setInterpolator(new BounceInterpolator());
            animator.start();
        }
    };
    private View.OnClickListener btnScaleClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ValueAnimator animator = ValueAnimator.ofInt(0, 35);
            animator.setDuration(4000);
            animator.setRepeatCount(1);
            animator.setRepeatMode(ObjectAnimator.REVERSE);
            animator.setInterpolator(new LinearInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int val = (Integer)animation.getAnimatedValue();
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30 + val);
                }
            });
            animator.start();
        }
    };
    private View.OnClickListener btnShiftClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            float x, xEnd1, xEnd2;
            x = textView.getX();
            xEnd1 = 0;
            xEnd2 = layout.getWidth() - textView.getWidth();

            ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "x", x, xEnd1);
            animator.setDuration(1000);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());

            ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView, "x", xEnd1, xEnd2);
            animator1.setDuration(3000);
            animator1.setInterpolator(new AccelerateDecelerateInterpolator());

            ObjectAnimator animator2 = ObjectAnimator.ofFloat(textView, "x", xEnd2, x);
            animator2.setDuration(1000);
            animator2.setInterpolator(new AccelerateDecelerateInterpolator());

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(animator, animator1, animator2);
            animatorSet.start();
            
        }
    };
    private View.OnClickListener btnChangeColoClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ValueAnimator animator = ValueAnimator.ofInt(0, 255);
            animator.setDuration(3000);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int val = (Integer)animation.getAnimatedValue();
                    textView.setTextColor(Color.rgb(val, 0, 0));
                }
            });
        }
    };
}