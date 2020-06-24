package com.example.alkoaplikacja;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Obrot extends AppCompatActivity {

    private Akcelerometr akcelerometr;
    private Zyroskop zyroskop;
    private ImageView StanUpojeniaIMG;
    private TextView LabelWarning, PomiarLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrot);

        StanUpojeniaIMG = findViewById(R.id.StanUpojenia);

        LabelWarning = findViewById(R.id.LabelWarning);
        PomiarLabel = findViewById(R.id.PomiarLabel);

        akcelerometr = new Akcelerometr(this);
        zyroskop = new Zyroskop(this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    StanUpojeniaIMG.setImageResource(R.drawable.pijany);
                    LabelWarning.setText("Pijany!");
                    PomiarLabel.setText("Wynik pomiaru:");

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
        }, 6000);



        zyroskop.setListener(new Zyroskop.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                if(rz > 3f || rz < -3f){
                    //getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    DobryObrot();
                    handler.removeCallbacksAndMessages(null);
                }

                else if(rz < 1.7f & rz > 0.5f || rz > -1.7f & rz < -0.5f || rx < 1.7f & rx > 0.5f || rx > -1.7f & rx < -0.5f ){
                    LabelWarning.setText("Szybciej się obracaj!");
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
    public void DobryObrot(){
        StanUpojeniaIMG.setImageResource(R.drawable.trzezwy);
        LabelWarning.setText("Trzeźwy!");
        PomiarLabel.setText("Wynik pomiaru:");
    }
}

