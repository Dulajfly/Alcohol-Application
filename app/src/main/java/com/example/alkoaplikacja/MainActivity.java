package com.example.alkoaplikacja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgButtonTurnOn, Button_Reakcja, Button_Obrot;

    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer zabawaMP = MediaPlayer.create(this, R.raw.muzyka_start);

        Button_Reakcja = (ImageButton) findViewById(R.id.Button_Reakcja);
        Button_Reakcja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReakcja();
            }
        });

        Button_Obrot = (ImageButton) findViewById(R.id.Button_Obrot);
        Button_Obrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openObrot();
            }
        });

        imgButtonTurnOn = (ImageButton) findViewById(R.id.imgButtonTurnOn);
        imgButtonTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zabawaMP.start();

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        openAlkomat();
                    }
                }, 3000);
            }
        });
    }

    public void openAlkomat(){
        Intent intent = new Intent(this, Alkomat.class);
        startActivity(intent);

    }

    public void openReakcja(){
        Intent intentReakcja = new Intent(this, Refleks.class);
        startActivity(intentReakcja);
    }

    public void openObrot(){
        Intent intentObrot = new Intent(this, Obrot.class);
        startActivity(intentObrot);
    }
}

