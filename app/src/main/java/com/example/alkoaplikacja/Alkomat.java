package com.example.alkoaplikacja;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Handler;



public class Alkomat extends AppCompatActivity {

    private Akcelerometr akcelerometr;
    private Zyroskop zyroskop;
    private ImageView StanUpojeniaIMG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alkomat);

        StanUpojeniaIMG = findViewById(R.id.StanUpojenia);

        final MediaPlayer pijanyMP = MediaPlayer.create(this, R.raw.pijany);
        final MediaPlayer trzezwyMP = MediaPlayer.create(this, R.raw.trzezwy);

        akcelerometr = new Akcelerometr(this);
        zyroskop = new Zyroskop(this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                trzezwyMP.start();
                StanUpojeniaIMG.setImageResource(R.drawable.trzezwy);
                akcelerometr.setListener(new Akcelerometr.Listener() {
                    @Override
                    public void onTranslation(float tx, float ty, float tz) {
                    }
                });
                zyroskop.setListener(new Zyroskop.Listener() {
                    @Override
                    public void onRotation(float rx, float ry, float rz) {
                    }
                });
            }
        }, 5000);

        akcelerometr.setListener(new Akcelerometr.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if(tx>1.2f || tx < -1.2f){
                    pijanyMP.start();
                    StanUpojeniaIMG.setImageResource(R.drawable.pijany);
                    handler.removeCallbacksAndMessages(null);
                }
                else if(ty>1.2f || ty < -1.2f){
                    pijanyMP.start();
                    StanUpojeniaIMG.setImageResource(R.drawable.pijany);
                    handler.removeCallbacksAndMessages(null);
                }
                else if(tz>1.2f || tz < -1.2f){
                    pijanyMP.start();
                    StanUpojeniaIMG.setImageResource(R.drawable.pijany);
                    handler.removeCallbacksAndMessages(null);
                }
            }
        });

        zyroskop.setListener(new Zyroskop.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                if(rz > 1.2f || rz < -1.2f){
                    pijanyMP.start();
                    StanUpojeniaIMG.setImageResource(R.drawable.pijany);
                    handler.removeCallbacksAndMessages(null);
                }
                else if(rx > 1.2f || rx < -1.2f){
                    pijanyMP.start();
                    StanUpojeniaIMG.setImageResource(R.drawable.pijany);
                    handler.removeCallbacksAndMessages(null);
                }
                else if(ry > 1.2f || ry < -1.2f){
                    pijanyMP.start();
                    StanUpojeniaIMG.setImageResource(R.drawable.pijany);
                    handler.removeCallbacksAndMessages(null);
                }
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        akcelerometr.register();
        zyroskop.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        akcelerometr.unregister();
        zyroskop.unregister();
    }
}
