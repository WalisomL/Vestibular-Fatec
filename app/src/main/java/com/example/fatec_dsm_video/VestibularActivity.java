package com.example.fatec_dsm_video;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class VestibularActivity extends AppCompatActivity {

    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vestibular);

        btnVoltar = findViewById(R.id.btnVoltarVestibular);

        btnVoltar.setOnClickListener(v -> finish());
    }
}