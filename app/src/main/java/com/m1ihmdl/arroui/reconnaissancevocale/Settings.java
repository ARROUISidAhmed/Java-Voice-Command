package com.m1ihmdl.arroui.reconnaissancevocale;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Settings extends AppCompatActivity {

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    Button avance, recule, droite, gauche, tourneDroite, tourneGauche, flip, etatUrgence, arreteToi;
    MediaRecorder mediaRecorder;
    String basePath;
    MediaPlayer mediaPlayer;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        avance = (Button) findViewById(R.id.avance);
        recule = (Button) findViewById(R.id.recule);
        droite = (Button) findViewById(R.id.droite);
        gauche = (Button) findViewById(R.id.gauche);
        tourneDroite = (Button) findViewById(R.id.tourneDroite);
        tourneGauche = (Button) findViewById(R.id.tourneGauche);
        flip = (Button) findViewById(R.id.flip);
        etatUrgence = (Button) findViewById(R.id.etatUrgence);
        arreteToi = (Button) findViewById(R.id.arreteToi);

        avance.setOnClickListener(new View.OnClickListener() {
            boolean clickedOnce = false;

            @Override
            public void onClick(View v) {
                if (clickedOnce) {
                    stopRecording();
                    clickedOnce = false;
                } else {
                    startRecording(basePath + "avance");
                    clickedOnce = true;
                }
            }
        });
        recule.setOnClickListener(new View.OnClickListener() {
            boolean clickedOnce = false;

            @Override
            public void onClick(View v) {
                if (clickedOnce) {
                    stopRecording();
                    clickedOnce = false;
                } else {
                    startRecording(basePath + "recule");
                    clickedOnce = true;
                }
            }
        });
        droite.setOnClickListener(new View.OnClickListener() {
            boolean clickedOnce = false;

            @Override
            public void onClick(View v) {
                if (clickedOnce) {
                    stopRecording();
                    clickedOnce = false;
                } else {
                    startRecording(basePath + "droite");
                    clickedOnce = true;
                }
            }
        });
        gauche.setOnClickListener(new View.OnClickListener() {
            boolean clickedOnce = false;

            @Override
            public void onClick(View v) {
                if (clickedOnce) {
                    stopRecording();
                    clickedOnce = false;
                } else {
                    startRecording(basePath + "gauche");
                    clickedOnce = true;
                }
            }
        });
        tourneGauche.setOnClickListener(new View.OnClickListener() {
            boolean clickedOnce = false;

            @Override
            public void onClick(View v) {
                if (clickedOnce) {
                    stopRecording();
                    clickedOnce = false;
                } else {
                    startRecording(basePath + "tourneGauche");
                    clickedOnce = true;
                }
            }
        });
        tourneDroite.setOnClickListener(new View.OnClickListener() {
            boolean clickedOnce = false;

            @Override
            public void onClick(View v) {
                if (clickedOnce) {
                    stopRecording();
                    clickedOnce = false;
                } else {
                    startRecording(basePath + "tourneDroite");
                    clickedOnce = true;
                }
            }
        });
        flip.setOnClickListener(new View.OnClickListener() {
            boolean clickedOnce = false;

            @Override
            public void onClick(View v) {
                if (clickedOnce) {
                    stopRecording();
                    clickedOnce = false;
                } else {
                    startRecording(basePath + "flip");
                    clickedOnce = true;
                }
            }
        });
        etatUrgence.setOnClickListener(new View.OnClickListener() {
            boolean clickedOnce = false;

            @Override
            public void onClick(View v) {
                if (clickedOnce) {
                    stopRecording();
                    clickedOnce = false;
                } else {
                    startRecording(basePath + "etatUrgence");
                    clickedOnce = true;
                }
            }
        });
        arreteToi.setOnClickListener(new View.OnClickListener() {
            boolean clickedOnce = false;

            @Override
            public void onClick(View v) {
                if (clickedOnce) {
                    stopRecording();
                    clickedOnce = false;
                } else {
                    startRecording(basePath + "arreteToi");
                    clickedOnce = true;
                }
            }
        });
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted) finish();

    }

    private void startRecording(String mFileName) {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(mFileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mediaRecorder.start();
    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }
}
