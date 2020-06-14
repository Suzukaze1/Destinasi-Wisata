package com.alvinmd.androidrplbo.activity.admin.misc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.activity.admin.konvensional.KonvensionalAdminActivity;
import com.alvinmd.androidrplbo.activity.admin.religi.ReligiAdminActivity;

public class TempatWisataAdminActivity extends AppCompatActivity {

    LinearLayout ll1, ll2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_wisata_admin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ll1 = findViewById(R.id.llAKonvensional);
        ll2 = findViewById(R.id.llAReligi1);

        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TempatWisataAdminActivity.this, KonvensionalAdminActivity.class);
                startActivity(i);
            }
        });

        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(TempatWisataAdminActivity.this, ReligiAdminActivity.class);
                startActivity(j);
            }
        });
    }
}
