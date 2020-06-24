package com.example.alkoaplikacja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Refleks extends AppCompatActivity {

    Button button_start, button_kliknij;
    long startTime, endTime, currentTime;
    public Random rng = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refleks);


        button_kliknij = (Button) findViewById(R.id.button_kliknij);
        button_start = (Button) findViewById(R.id.button_start);

        button_start.setEnabled(true);
        button_kliknij.setEnabled(false);

        button_start.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                int randomTime = rng.nextInt(6000) + 1000;
                button_start.setEnabled(false);
                button_kliknij.setText("");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startTime=System.currentTimeMillis();
                        button_kliknij.setBackgroundColor(Color.RED);
                        button_kliknij.setText("NACIŚNIJ");
                        button_kliknij.setEnabled(true);
                    }
                }, randomTime);

            }
        });

        button_kliknij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTime=System.currentTimeMillis();
                currentTime=endTime-startTime;
                button_kliknij.setBackgroundColor(Color.BLUE);

                button_start.setEnabled(true);
                button_kliknij.setEnabled(false);
                if(currentTime>750){
                    button_kliknij.setText("Twój czas to:\n"+ currentTime+" ms\n" + "JESTEŚ PIJANY ALBO NIESPEŁNA ROZUMU");
                }
                else{
                    button_kliknij.setText("Twój czas to:\n "+currentTime+" ms\n" + "Jesteś trzeźwy");
                }
            }
        });
    }
}
