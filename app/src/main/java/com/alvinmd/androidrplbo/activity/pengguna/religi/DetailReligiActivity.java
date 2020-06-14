package com.alvinmd.androidrplbo.activity.pengguna.religi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvinmd.androidrplbo.R;
import com.bumptech.glide.Glide;

public class DetailReligiActivity extends AppCompatActivity {

    TextView tvWisata, tvDesc, tvNav, tvNOTelp, tvEmail, tvLokasi;
    ImageView imgThumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_religi);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //menerima data
        String namaWisata = getIntent().getExtras().getString("tvHomestay");
        String deskripsiWisata = getIntent().getExtras().getString("tvDeskripsi");
        String thumbWisata = getIntent().getExtras().getString("imgThumb");
        final String lokasiWisata = getIntent().getExtras().getString("tvNavigasii");
        String notelpWisata = getIntent().getExtras().getString("notelp");
        String alamatWisata = getIntent().getExtras().getString("alamat");
        String emailWisata = getIntent().getExtras().getString("email");

        //inisialisasi
        tvWisata = findViewById(R.id.tvNamaDWisataR);
        tvDesc = findViewById(R.id.tv_DDeskripsiR);
        imgThumb = findViewById(R.id.imgDThumbR);
        tvNav = findViewById(R.id.btnDNavigasiR);
        tvNOTelp = findViewById(R.id.tv001R);
        tvEmail = findViewById(R.id.tv002R);
        tvLokasi = findViewById(R.id.tv003R);

        //set data
        tvWisata.setText(namaWisata);
        tvDesc.setText(deskripsiWisata);
        tvNOTelp.setText(notelpWisata);
        tvEmail.setText(alamatWisata);
        tvLokasi.setText(emailWisata);

        //gambar berupa glide
        Glide.with(this).load(thumbWisata).into(imgThumb);

        //navigasi
        tvNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geo = ("google.navigation:q="+lokasiWisata);
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(geo));
                startActivity(i);
            }
        });
    }
}
