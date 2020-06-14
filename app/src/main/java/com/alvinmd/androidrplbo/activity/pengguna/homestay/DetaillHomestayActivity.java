package com.alvinmd.androidrplbo.activity.pengguna.homestay;

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

import java.math.BigInteger;
import java.text.NumberFormat;

public class DetaillHomestayActivity extends AppCompatActivity {

    TextView tvWisata, tvDesc, tvNav, tvNoTelp, tvEmail, tvLokasi, tvDial, tvisiDial;
    ImageView imgThumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill_homestay);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //menerima data
        String namaWisata = getIntent().getExtras().getString("tvHomestayH");
        String deskripsiWisata = getIntent().getExtras().getString("tvDeskripsiH");
        String thumbWisata = getIntent().getExtras().getString("imgThumbH");
        final String lokasiWisata = getIntent().getExtras().getString("tvNavigasiiH");
        final String notelpWisata = getIntent().getExtras().getString("notelpH");
        String alamatWisata = getIntent().getExtras().getString("alamatH");
        String emailWisata = getIntent().getExtras().getString("emailH");

        //inisialisasi
        tvWisata = findViewById(R.id.tvNamaDWisataH);
        tvDesc = findViewById(R.id.tv_DDeskripsiH);
        imgThumb = findViewById(R.id.imgDThumbH);
        tvNav = findViewById(R.id.btnDNavigasiH);
        tvNoTelp = findViewById(R.id.tv001H);
        tvEmail = findViewById(R.id.tv002H);
        tvLokasi = findViewById(R.id.tv003H);
        tvDial = findViewById(R.id.btnTelpH);
        tvisiDial = findViewById(R.id.tel);

        //set data
        tvWisata.setText(namaWisata);
        tvDesc.setText(deskripsiWisata);
        tvNoTelp.setText(notelpWisata);
        tvEmail.setText(alamatWisata);
        tvLokasi.setText(emailWisata);
        tvisiDial.setText(notelpWisata);

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

        tvDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = notelpWisata;
                BigInteger myNum = new BigInteger(a);

                String tel = ("tel:"+0+myNum);
                Intent j = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                startActivity(j);
            }
        });
    }
}
