package com.m1ihmdl.arroui.reconnaissancevocale;

import android.Manifest;
import android.app.Dialog;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class RecordingActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION = 1;
    private MediaRecorder mRecorder;
    private String basePath;
    private Dialog myDialog;
    private boolean permissionToRecordAccepted = false;
    private boolean recording = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION);
        basePath = getExternalCacheDir().getAbsolutePath();
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.custompopup);

        Button avance = findViewById(R.id.avance);
        Button recule = findViewById(R.id.recule);
        Button droite = findViewById(R.id.droite);
        Button gauche = findViewById(R.id.gauche);
        Button tourneDroite = findViewById(R.id.tourneDroite);
        Button tourneGauche = findViewById(R.id.tourneGauche);
        Button flip = findViewById(R.id.flip);
        Button etatUrgence = findViewById(R.id.etatUrgence);
        Button arreteToi = findViewById(R.id.arreteToi);

        Button cancelpopupButton = myDialog.findViewById(R.id.cancelpopup);
        cancelpopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
                recording = false;
                myDialog.dismiss();
            }
        });
        Button stopRecordingButton =
                myDialog.findViewById(R.id.stopRecording);
        stopRecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
                recording = false;
                myDialog.dismiss();
            }
        });

        avance.setOnClickListener(new View.OnClickListener() {
            String fileOutPutName = "avance";

            public void onClick(View v) {
                TextView text = myDialog.findViewById(R.id.ordre);
                text.setText("Veuillez prononcer le mot avance");
                startRecording(fileOutPutName);
                myDialog.show();
            }
        });
        recule.setOnClickListener(new View.OnClickListener() {
            String fileOutPutName = "recule";

            public void onClick(View v) {
                ((TextView) myDialog.findViewById(R.id.ordre)).setText("Veuillez prononcer le mot recule");
                startRecording(fileOutPutName);
                myDialog.show();
            }
        });
        droite.setOnClickListener(new View.OnClickListener() {
            String fileOutPutName = "droite";

            public void onClick(View v) {
                ((TextView) myDialog.findViewById(R.id.ordre)).setText("Veuillez prononcer le mot droite");
                startRecording(fileOutPutName);
                myDialog.show();
            }
        });
        gauche.setOnClickListener(new View.OnClickListener() {
            String fileOutPutName = "gauche";

            public void onClick(View v) {
                ((TextView) myDialog.findViewById(R.id.ordre)).setText("Veuillez prononcer le mot gauche");
                startRecording(fileOutPutName);
                myDialog.show();
            }
        });
        tourneGauche.setOnClickListener(new View.OnClickListener() {

            String fileOutPutName = "tourneGauche";

            public void onClick(View v) {
                ((TextView) myDialog.findViewById(R.id.ordre)).setText("Veuillez prononcer le mot tourneGauche");
                startRecording(fileOutPutName);
                myDialog.show();
            }
        });
        tourneDroite.setOnClickListener(new View.OnClickListener() {
            String fileOutPutName = "tourneDroite";

            public void onClick(View v) {
                ((TextView) myDialog.findViewById(R.id.ordre)).setText("Veuillez prononcer le mot tourneDroite");
                startRecording(fileOutPutName);
                myDialog.show();
            }
        });
        flip.setOnClickListener(new View.OnClickListener() {
            String fileOutPutName = "flip";

            public void onClick(View v) {
                ((TextView) myDialog.findViewById(R.id.ordre)).setText("Veuillez prononcer le mot flip");
                startRecording(fileOutPutName);
                myDialog.show();
            }
        });
        etatUrgence.setOnClickListener(new View.OnClickListener() {
            String fileOutPutName = "etatUrgence";

            public void onClick(View v) {
                ((TextView) myDialog.findViewById(R.id.ordre)).setText("Veuillez prononcer le mot etatUrgence");
                startRecording(fileOutPutName);
                myDialog.show();
            }
        });
        arreteToi.setOnClickListener(new View.OnClickListener() {
            String fileOutPutName = "arreteToi";

            public void onClick(View v) {
                ((TextView) myDialog.findViewById(R.id.ordre)).setText("Veuillez prononcer le mot arreteToi");
                startRecording(fileOutPutName);
                myDialog.show();
            }
        });
    }


    private void startRecording(String mFileName) {
        if (!recording) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mRecorder.setOutputFile(basePath + "/" + mFileName + ".mp4");
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }

            mRecorder.start();
            recording = true;
        }

    }

    private void stopRecording() {
        if (recording) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION) {
        }
    }


}
