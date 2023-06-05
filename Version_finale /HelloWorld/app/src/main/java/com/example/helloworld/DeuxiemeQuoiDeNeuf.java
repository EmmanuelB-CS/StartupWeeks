package com.example.helloworld;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

import java.util.HashMap;
import java.util.Map;

import backend.SendDataToServerTask;
import backend.CreateGraph;

public class DeuxiemeQuoiDeNeuf extends AppCompatActivity {

    private EditText editTextActivity;
    private EditText editTextDuration;
    private EditText editTextBeneficialEffects;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deuxieme_quoi_de_neuf);

        editTextActivity = findViewById(R.id.editTextActivity);
        editTextDuration = findViewById(R.id.editTextDuration);
        editTextBeneficialEffects = findViewById(R.id.editTextBeneficialEffects);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String activity = editTextActivity.getText().toString();
                String duration = editTextDuration.getText().toString();
                String yesNo = editTextBeneficialEffects.getText().toString();

                String message = "Activité: " + activity + "\nDurée: " + duration + " minutes" +
                        "\nRéponse (oui/non): " + yesNo;
                Toast.makeText(DeuxiemeQuoiDeNeuf.this, message, Toast.LENGTH_SHORT).show();

                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("intitule", activity);
                dataMap.put("duree", duration);
                dataMap.put("effet", yesNo);

                SendDataToServerTask sendDataToServerTask = new SendDataToServerTask(DeuxiemeQuoiDeNeuf.this, "http://138.195.52.137:5000/enregistrer_sport");
                sendDataToServerTask.sendDataToServer(dataMap);
            }
        });

        editTextBeneficialEffects.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // Avant la modification du texte
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Pendant la modification du texte
                String input = charSequence.toString().toLowerCase();

                if (!input.equals("oui") && !input.equals("non")) {
                    editTextBeneficialEffects.setError("Veuillez entrer oui ou non");
                } else {
                    editTextBeneficialEffects.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Après la modification du texte
            }
        });
    }
}
