package com.example.fatec_dsm_video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoHeaderDsn;
    private Button btnVestibular, btnVideoInstitucional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoHeaderDsn = findViewById(R.id.videoHeaderDsn);
        btnVestibular = findViewById(R.id.btnVestibular);
        btnVideoInstitucional = findViewById(R.id.btnVideoInstitucional);

        // Configura e inicia o vídeo DSN
        Uri uriDsn = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dsn);
        videoHeaderDsn.setVideoURI(uriDsn);
        videoHeaderDsn.setOnPreparedListener(mp -> {
            mp.setVolume(0f, 0f);
            mp.setLooping(true);
        });
        videoHeaderDsn.start();

        btnVestibular.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VestibularActivity.class);
            startActivity(intent);
        });

        btnVideoInstitucional.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
            startActivity(intent);
        });
    }
}