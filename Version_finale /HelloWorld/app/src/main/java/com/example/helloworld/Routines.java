package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.net.Uri;
import android.widget.VideoView;


public class Routines extends AppCompatActivity {
    private VideoView videoView;
    private static final int REQUEST_CODE_PERMISSION = 1001;

    private void lireVideo() {
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.videokine;
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);

        videoView = findViewById(R.id.videoView);

        // Vérifier la permission READ_EXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // La permission n'est pas accordée, vous devez demander à l'utilisateur de l'autoriser.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION);
        } else {
            // La permission est déjà accordée, vous pouvez accéder au stockage externe.
            lireVideo();
        }
    }

    public void RetourALaPageDaccueil6(View view) {
        Intent messageverspageaccueil = new Intent();
        messageverspageaccueil.setClass(this, MainActivity.class);
        startActivity(messageverspageaccueil);
    }
}
