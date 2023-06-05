package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    public void ChangerPageConnexion(View view){
        Intent messageversaccueil=new Intent();
        messageversaccueil.setClass(this,MainActivity.class);
        startActivity(messageversaccueil);
    }

    public void ChangerPageInscription(View view){
        Intent messageversinscription=new Intent();
        messageversinscription.setClass(this,Inscription.class);
        startActivity(messageversinscription);
    }
}