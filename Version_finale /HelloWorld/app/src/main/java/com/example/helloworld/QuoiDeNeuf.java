package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuoiDeNeuf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quoi_de_neuf);
    }

    public void RetourALaPageDaccueil2(View view) {
        Intent messageverspageaccueil = new Intent();
        messageverspageaccueil.setClass(this, MainActivity.class);
        startActivity(messageverspageaccueil);
    }
}