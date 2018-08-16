package com.example.com.plaython;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView mbottle,toastImage;
    private Random mrandom = new Random();
    private int lastDir;
    private boolean spinning;
    private TextView mtv;
    private int[] options2 ={R.drawable.ic_mood_bad, R.drawable.ic_mood_good};
    private String[] options = {"Truth", "Dare"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mbottle = findViewById(R.id.bottle1);



    }

    public void Button(View view) {
        ImageButton iv = findViewById(R.id.btn);
        final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);

        Animation fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        iv.startAnimation(fade);

        if (!spinning) {

            int newDir = mrandom.nextInt(4500);
            float pivotX = mbottle.getWidth() / 2;
            float pivotY = mbottle.getHeight() / 2;

            final int newDir1 = mrandom.nextInt(2);







            Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
            rotate.setDuration(2500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mediaPlayer.start();
                    spinning = true;

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    mediaPlayer.pause();
                    spinning = false;
                    showToast();
                    mtv.setText(options[newDir1]);
                    toastImage.setImageResource(options2[newDir1]);


                }


                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastDir = newDir;
            mbottle.startAnimation(rotate);




        }


    }

    public void showToast() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.toast_layout,(ViewGroup)findViewById(R.id.toast_root));

        mtv = layout.findViewById(R.id.toast_text);
        toastImage = layout.findViewById(R.id.toast_image);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}