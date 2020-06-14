package com.alvinmd.androidrplbo.activity.admin.konvensional;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alvinmd.androidrplbo.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UbahKonvensionalActivity extends AppCompatActivity {

    EditText etNama, etGambar, etDeskripsi, etAlamat, etNomorTelefon, etKoordinat, etEmail;
    TextView tvId;
    Button btnSimpann;

    private final String UbahKonvensional = "https://suzukaze.000webhostapp.com/UbahWisataKonvensional.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_konvensional);

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
        String idwisata = getIntent().getExtras().getString("id");

        etNama = findViewById(R.id.etNamaWisataKE);
        etGambar = findViewById(R.id.etGambarWisataKE);
        etDeskripsi = findViewById(R.id.etDeskripsiKE);
        etAlamat = findViewById(R.id.etAlamatKE);
        etNomorTelefon = findViewById(R.id.etNotelpKE);
        etKoordinat = findViewById(R.id.etGoogleMapsKE);
        etEmail = findViewById(R.id.etEmailKE);
        btnSimpann = findViewById(R.id.btnKirimKE);
        tvId = findViewById(R.id.tvIDE);

        etNama.setText(namaWisata);
        etGambar.setText(thumbWisata);
        etDeskripsi.setText(deskripsiWisata);
        etAlamat.setText(alamatWisata);
        etNomorTelefon.setText(notelpWisata);
        etKoordinat.setText(lokasiWisata);
        etEmail.setText(emailWisata);
        tvId.setText(idwisata);

        btnSimpann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubahData();
            }
        });
    }

    private void ubahData() {
        final String nama_wisata = this.etNama.getText().toString().trim();
        final String deskripsi_wisata = this.etDeskripsi.getText().toString().trim();
        final String lokasi_wisata = this.etKoordinat.getText().toString().trim();
        final String thumb_wisata = this.etGambar.getText().toString().trim();
        final String nomor_telfon_wisata = this.etNomorTelefon.getText().toString().trim();
        final String alamat_wisata = this.etAlamat.getText().toString().trim();
        final String email_wisata = this.etEmail.getText().toString().trim();
        final String id = this.tvId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UbahKonvensional,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(UbahKonvensionalActivity.this, "Masuk pak eko", Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(UbahKonvensionalActivity.this, "Data Berhasil Di Ubah", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(UbahKonvensionalActivity.this, "gagal pak eko", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UbahKonvensionalActivity.this, "Gagal Mengubah Data" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UbahKonvensionalActivity.this, "Cek Kembali" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("nama_wisata", nama_wisata);
                params.put("deskripsi_wisata", deskripsi_wisata);
                params.put("lokasi_wisata", lokasi_wisata);
                params.put("thumb_wisata", thumb_wisata);
                params.put("nomor_telfon_wisata", nomor_telfon_wisata);
                params.put("alamat_wisata", alamat_wisata);
                params.put("email_wisata", email_wisata);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
