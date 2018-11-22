package com.m1ihmdl.arroui.reconnaissancevocale;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AudioPlayTest";
    private List<String> fileList = new ArrayList<>();
    private MediaPlayer mPlayer;
    private String basePath;
    private static final int REQUEST_READ_EXTERNAL_STORAGE_PERMISSION = 1;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v(LOG_TAG, "Permission is granted1");
            } else {
                Log.v(LOG_TAG, "Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
            }
        }

        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, permissions, REQUEST_READ_EXTERNAL_STORAGE_PERMISSION);
        basePath = getExternalCacheDir().getAbsolutePath();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final File base = new File(basePath);
        Button checkFiles = findViewById(R.id.checkFiles);
        final ListView listView = findViewById(R.id.listView);
        checkFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDir(base);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        for (File file : base.listFiles()) {
                            if (file.getName().equals(listView.getItemAtPosition(position).toString())) {
                                Log.d(LOG_TAG, listView.getItemAtPosition(position).toString() + " " + file.getPath());
                                mPlayer = new MediaPlayer();
                                try {
                                    mPlayer.setDataSource(file.getAbsolutePath());
                                    mPlayer.prepare();
                                    Log.d(LOG_TAG, "Setting absolute path");
                                } catch (IOException e) {
                                    Log.d(LOG_TAG, "prepare() failed");
                                }
                                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {

                                        Log.d(LOG_TAG, "completion " + mPlayer.toString());
                                        stopPlaying();
                                    }
                                });
                                mPlayer.start();
                            }
                        }
                    }


                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, RecordingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void stopPlaying() {
        mPlayer.release();
    }


    void listDir(File f) {
        ListView listView = findViewById(R.id.listView);
        File[] files = f.listFiles();
        fileList.clear();
        for (File file : files) {
            fileList.add(file.getName());
        }
        ArrayAdapter<String> directoryList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fileList);
        listView.setAdapter(directoryList);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                Log.d(LOG_TAG, "External storage2");
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v(LOG_TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
                    //resume tasks needing this permission
                }
                break;
        }
    }

}
