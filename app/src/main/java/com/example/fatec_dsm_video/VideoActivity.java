package com.example.fatec_dsm_video;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoPlayerFatec;
    private SeekBar seekBarVideo;
    private Button btnPlayPause, btnVoltarVideo;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoPlayerFatec = findViewById(R.id.videoPlayerFatec);
        seekBarVideo = findViewById(R.id.seekBarVideo);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnVoltarVideo = findViewById(R.id.btnVoltarVideo);

        Uri uriFatec = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fatec);
        videoPlayerFatec.setVideoURI(uriFatec);

        videoPlayerFatec.setOnPreparedListener(mp -> {
            seekBarVideo.setMax(videoPlayerFatec.getDuration());
            videoPlayerFatec.start();
            atualizarSeekBar();
        });

        btnPlayPause.setOnClickListener(v -> {
            if (videoPlayerFatec.isPlaying()) {
                videoPlayerFatec.pause();
                btnPlayPause.setText("CONTINUAR");
            } else {
                videoPlayerFatec.start();
                btnPlayPause.setText("PAUSAR");
                atualizarSeekBar();
            }
        });

        seekBarVideo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoPlayerFatec.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnVoltarVideo.setOnClickListener(v -> {
            videoPlayerFatec.stopPlayback();
            finish();
        });
    }

    private void atualizarSeekBar() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (videoPlayerFatec != null && videoPlayerFatec.isPlaying()) {
                    seekBarVideo.setProgress(videoPlayerFatec.getCurrentPosition());
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoPlayerFatec.isPlaying()) {
            videoPlayerFatec.pause();
            btnPlayPause.setText("CONTINUAR");
        }
    }
}