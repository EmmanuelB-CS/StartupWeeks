package backend;

/*  <-- supprimer pour décommenter le code (ATTENTION: il y en a un aussi ligne 90

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Question pour Mme Galtier (encadrante 2023): comment fait on pour envoyer les pas à intervalles de temps
// régulier, parce qu'ici on sensor changed s'active dès qu'un event est détecté, on peut peut-être
// utiliser des callbacks?

// De manière plus générale le on create empêche le décompte des pas quand l'app est éteinte, est ce que
// l'on peut créer un mécanisme en arrière plan qui envoie régulièrement le nombre de pas?
// Une alternative serait de créer un bouton "Démarrer" qui enregistre les pas pendant une séance de marche
// pas très ergonomique mais bon... à la guerre comme à la guerre!

public class StepCounterActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor stepSensor;
    private boolean isCounting = false; // Variable pour suivre l'état du décompte (false si je compte pas true sinon)
    private SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCounting) {
                    startStepCounter();
                    isCounting = true;
                    startButton.setText("Arrêter"); // Mettre à jour le texte du bouton
                } else {
                    stopStepCounter();
                    isCounting = false;
                    startButton.setText("Démarrer"); // Mettre à jour le texte du bouton
                }
            }
        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        // Vérifier si le capteur de pas est disponible et mettre à jour le texte du bouton en conséquence
        if (stepSensor == null) {
            startButton.setEnabled(false);
            startButton.setText("Capteur indisponible");
        }
    }

    private void startStepCounter() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float steps = event.values[0];
                sendStepsToServer(String.valueOf(steps));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Rien à faire ici pour l'instant
            }
        };

        sensorManager.registerListener(sensorEventListener, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stopStepCounter() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    private void sendStepsToServer(String steps) {
        SendDataToServerTask task = new SendDataToServerTask(StepCounterActivity.this, "http://192.168.1.41:5000/enregistrer/TryNorris/data_value");
        task.execute(steps);
    }

}

*/