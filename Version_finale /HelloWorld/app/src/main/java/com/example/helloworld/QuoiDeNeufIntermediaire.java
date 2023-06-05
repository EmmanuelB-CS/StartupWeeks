package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuoiDeNeufIntermediaire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quoi_de_neuf_intermediaire);
    }
    public void ChangerPageBlessure(View view){
        Intent messageversQuoiDeNeuf=new Intent();
        messageversQuoiDeNeuf.setClass(this,QuoiDeNeuf.class);
        startActivity(messageversQuoiDeNeuf);
    }

    public void ChangerPageactiviteSportive(View view){
        Intent messageversDeuxiemeQuoiDeNeuf=new Intent();
        messageversDeuxiemeQuoiDeNeuf.setClass(this,DeuxiemeQuoiDeNeuf.class);
        startActivity(messageversDeuxiemeQuoiDeNeuf);
    }
    public void ChangerPageCycleMenstruel(View view){
        Intent messageversCycleMenstruel=new Intent();
        messageversCycleMenstruel.setClass(this,CycleMenstruel.class);
        startActivity(messageversCycleMenstruel);
    }

}