package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SeekBar seekBar=findViewById(R.id.sb_seekBar);
        TextView textView = findViewById(R.id.textviewscore);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"Niveau:" + progress, Toast.LENGTH_SHORT).show();
                textView.setText("Niveau psychologique :" + String.valueOf(progress));
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

        SeekBar seekBar2=findViewById(R.id.sb_seekBarPhysique);
        TextView textView2=findViewById(R.id.textviewscorephysique);

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"Niveau:" + progress, Toast.LENGTH_SHORT).show();
                textView2.setText("Niveau physique :" + String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {
                // Toast.makeText(getApplicationContext(),"Touchez le curseur et glissez", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {
                //Toast.makeText(getApplicationContext(),"Niveau finalement selectionné:"+ seekBar.getProgress(), Toast.LENGTH_SHORT).show();

            }
        });

        SeekBar seekBar3=findViewById(R.id.sb_seekBarDouleur);
        TextView textView3=findViewById(R.id.textviewscoredouleur);

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar3, int progress, boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"Niveau:" + progress, Toast.LENGTH_SHORT).show();
                textView3.setText("Des douleurs :" + String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar3) {
                // Toast.makeText(getApplicationContext(),"Touchez le curseur et glissez", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar3) {
                //Toast.makeText(getApplicationContext(),"Niveau finalement selectionné:"+ seekBar.getProgress(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void changerQuestionnaireHebdo(View view){
        Intent messageversQuestionnaireHebdo=new Intent();
        messageversQuestionnaireHebdo.setClass(this,QuestionnaireHebdo.class);
        startActivity(messageversQuestionnaireHebdo);

    }
    public void changerQuoiDeNeuf(View view){
        Intent messageversQuoiDeNeufIntermédiaire=new Intent();
        messageversQuoiDeNeufIntermédiaire.setClass(this,QuoiDeNeufIntermediaire.class);
        startActivity(messageversQuoiDeNeufIntermédiaire);

    }

    public void changerCarnetDeSante(View view){
        Intent messageverscarnetdesante=new Intent();
        messageverscarnetdesante.setClass(this,CarnetDeSante.class);
        startActivity(messageverscarnetdesante);

    }

    public void changerMonTraitement(View view){
        Intent messageversmontraitement=new Intent();
        messageversmontraitement.setClass(this,MonTraitement.class);
        startActivity(messageversmontraitement);

    }

    public void changerRoutines(View view){
        Intent messageversroutines=new Intent();
        messageversroutines.setClass(this,Routines.class);
        startActivity(messageversroutines);

    }
}


