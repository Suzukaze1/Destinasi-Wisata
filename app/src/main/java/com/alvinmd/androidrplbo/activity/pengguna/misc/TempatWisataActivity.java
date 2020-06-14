package com.alvinmd.androidrplbo.activity.pengguna.misc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.activity.pengguna.konvensional.KonvensionalActivity;
import com.alvinmd.androidrplbo.activity.pengguna.religi.ReligiActivity;

public class TempatWisataActivity extends AppCompatActivity {

    LinearLayout ll01, ll02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_wisata);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ll01 = findViewById(R.id.llKonvensional);
        ll02 = findViewById(R.id.llReligi);

        ll01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(TempatWisataActivity.this, KonvensionalActivity.class);
                startActivity(k);
            }
        });

        ll02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent h = new Intent(TempatWisataActivity.this, ReligiActivity.class);
                startActivity(h);
            }
        });
    }
}
