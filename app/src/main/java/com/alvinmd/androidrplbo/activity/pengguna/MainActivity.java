package com.alvinmd.androidrplbo.activity.pengguna;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.activity.admin.misc.RegisterActivity;
import com.alvinmd.androidrplbo.activity.pengguna.homestay.HomestayActivity;
import com.alvinmd.androidrplbo.activity.pengguna.misc.AboutActivity;
import com.alvinmd.androidrplbo.activity.pengguna.misc.LoginActivity;
import com.alvinmd.androidrplbo.activity.pengguna.misc.TempatWisataActivity;
import com.alvinmd.androidrplbo.activity.pengguna.restoran.RestoranActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    LinearLayout lladmin, llWisata, llAbout, llHomestay, llRestoran;
    CardView cvLogout;
    Button btnLogin;
    CircleImageView ci1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ci1 = findViewById(R.id.img1);
        btnLogin = findViewById(R.id.btnlogin);
        llWisata = findViewById(R.id.llWisata);
        llAbout = findViewById(R.id.llAbout);
        llHomestay = findViewById(R.id.llHomestay);
        llRestoran = findViewById(R.id.llRestoran);

        ci1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogin();
            }
        });

        llWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWisata();
            }
        });

        llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAbout();
            }
        });

        llHomestay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHomestay();
            }
        });

        llRestoran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRestoran();
            }
        });
    }

    private void showRestoran() {
        Intent restoran = new Intent(MainActivity.this, RestoranActivity.class);
        startActivity(restoran);
    }

    private void showHomestay() {
        Intent homestay = new Intent(MainActivity.this, HomestayActivity.class);
        startActivity(homestay);
    }

    private void showAbout() {
        Intent about = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(about);
    }

    private void showWisata() {
        Intent wisata = new Intent(MainActivity.this, TempatWisataActivity.class);
        startActivity(wisata);
    }

    private void showLogin() {
        Intent showLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(showLogin);
    }
}
