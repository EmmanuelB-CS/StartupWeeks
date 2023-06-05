package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import backend.CreateGraph;

public class CarnetDeSante extends AppCompatActivity {

    private Button buttonDisplay;
    private ImageView imageViewGraph;
    private Button RetourAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carnet_de_sante);

        buttonDisplay = findViewById(R.id.buttonDisplay);
        imageViewGraph = findViewById(R.id.imageView);
        RetourAccueil = findViewById(R.id.RetourAccueil);

        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appel de la méthode displayGraph de CreateGraph pour afficher le graphique
                CreateGraph.displayGraph(imageViewGraph);
            }
        });

        RetourAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageverspageaccueil = new Intent(CarnetDeSante.this, MainActivity.class);
                startActivity(messageverspageaccueil);
            }
        });
    }
}
