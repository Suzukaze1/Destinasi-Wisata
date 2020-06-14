package com.alvinmd.androidrplbo.activity.admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.activity.admin.homestay.HomestayAdminActivity;
import com.alvinmd.androidrplbo.activity.admin.misc.TempatWisataAdminActivity;
import com.alvinmd.androidrplbo.activity.admin.restoran.RestoranAdminActivity;

public class AdminActivity extends AppCompatActivity {

    LinearLayout btnlogout, llAKonvensional, llhomestay, llRestoranAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnlogout = findViewById(R.id.llLogout);
        llAKonvensional = findViewById(R.id.llAdminWisata);
        llhomestay = findViewById(R.id.llHomestayAdmin);
        llRestoranAdmin = findViewById(R.id.llRestoranAdmin);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminActivity.this, "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        llAKonvensional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, TempatWisataAdminActivity.class);
                startActivity(i);
            }
        });

        llhomestay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(AdminActivity.this, HomestayAdminActivity.class);
                startActivity(j);
            }
        });

        llRestoranAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(AdminActivity.this, RestoranAdminActivity.class);
                startActivity(k);
            }
        });
    }
}
