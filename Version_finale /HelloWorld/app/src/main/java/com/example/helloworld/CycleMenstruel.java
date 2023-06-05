package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CycleMenstruel extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    protected CalendarView periodsCalendar;
    protected SeekBar seekBar1;
    protected TextView textView1;
    protected SeekBar seekBar2;
    protected TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_menstruel);

        periodsCalendar = findViewById(R.id.periodsCalendar);
        // focus the calendar view on today date
        periodsCalendar.setDate(Calendar.getInstance().getTimeInMillis(), false, true);
        // attach a listener to react to a new date selection
        periodsCalendar.setOnDateChangeListener(this);

        seekBar1 = findViewById(R.id.sb_seekBarIntensite);
        textView1 = findViewById(R.id.textviewscoreintensitemenstuel);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"Niveau:" + progress, Toast.LENGTH_SHORT).show();
                textView1.setText("Intensité :" + String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Toast.makeText(getApplicationContext(),"Touchez le curseur et glissez", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(),"Niveau finalement selectionné:"+ seekBar.getProgress(), Toast.LENGTH_SHORT).show();

            }
        });

        seekBar2 = findViewById(R.id.sb_seekBarDouleur);
        textView2 = findViewById(R.id.textviewscoredouleurmenstruel);

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"Niveau:" + progress, Toast.LENGTH_SHORT).show();
                textView2.setText("Douleur :" + String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Toast.makeText(getApplicationContext(),"Touchez le curseur et glissez", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(),"Niveau finalement selectionné:"+ seekBar.getProgress(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void RetourALaPageDaccueil7(View view) {
        Intent messageverspageaccueil = new Intent();
        messageverspageaccueil.setClass(this, MainActivity.class);
        startActivity(messageverspageaccueil);
    }

    // appelée quand l'utilisatrice sélectionne une date sur le calendrier
    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
        Toast.makeText(getApplicationContext(), "Début des règles le " + dayOfMonth + "/" + (month + 1), Toast.LENGTH_SHORT).show();
    }
}
