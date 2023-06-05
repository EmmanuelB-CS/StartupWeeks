package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DeuxiemeQuestionnaireHebdo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deuxieme_questionnaire_hebdo);
    }

    public void ChangerPageDansQuestionnaireHebdo2(View view){
        Intent messageverspage3=new Intent();
        messageverspage3.setClass(this,TroisiemeQuestionnaireMensuel.class);
        startActivity(messageverspage3);

    }
}