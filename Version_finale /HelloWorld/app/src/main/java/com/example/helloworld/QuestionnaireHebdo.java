package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuestionnaireHebdo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_hebdo);
    }

    public void ChangerPageDansQuestionnaireHebdo(View view){
        Intent messageverspagesuivante=new Intent();
        messageverspagesuivante.setClass(this,DeuxiemeQuestionnaireHebdo.class);
        startActivity(messageverspagesuivante);

    }
}