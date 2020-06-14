package com.alvinmd.androidrplbo.activity.pengguna.restoran;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alvinmd.androidrplbo.R;
import com.bumptech.glide.Glide;

public class DetailRestoranActivity extends AppCompatActivity {

    TextView tvWisata, tvDesc, tvNav, tvNOTelp, tvEmail, tvLokasi;
    int i;
    ImageView imgThumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restoran);

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
        tvWisata = findViewById(R.id.tvNamaDWisataRes);
        tvDesc = findViewById(R.id.tv_DDeskripsiRes);
        imgThumb = findViewById(R.id.imgDThumbRes);
        tvNav = findViewById(R.id.btnDNavigasiRes);
        tvNOTelp = findViewById(R.id.tv001Res);
        tvEmail = findViewById(R.id.tv002Res);
        tvLokasi = findViewById(R.id.tv003Res);

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
