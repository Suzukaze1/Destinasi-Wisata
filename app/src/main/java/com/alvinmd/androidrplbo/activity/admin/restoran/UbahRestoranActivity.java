package com.alvinmd.androidrplbo.activity.admin.restoran;

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

public class UbahRestoranActivity extends AppCompatActivity {

    EditText etNama, etGambar, etDeskripsi, etAlamat, etNomorTelefon, etKoordinat, etEmail;
    TextView tvId, tvNon;
    Button btnSimpann;

    private final String URL_UbahRestoran = "https://suzukaze.000webhostapp.com/UbahRestoran.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restoran);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //menerima data
        String namaRestoran = getIntent().getExtras().getString("tvHomestay");
        String deskripsiRestoran = getIntent().getExtras().getString("tvDeskripsi");
        String thumbRestoran = getIntent().getExtras().getString("imgThumb");
        final String lokasiRestoran = getIntent().getExtras().getString("tvNavigasii");
        String notelpRestoran = getIntent().getExtras().getString("notelp");
        String alamatRestoran = getIntent().getExtras().getString("alamat");
        String emailRestoran = getIntent().getExtras().getString("email");
        String idRestoran = getIntent().getExtras().getString("id");

        etNama = findViewById(R.id.etNamaWisataResE);
        etGambar = findViewById(R.id.etGambarWisataResE);
        etDeskripsi = findViewById(R.id.etDeskripsiResE);
        etAlamat = findViewById(R.id.etAlamatResE);
        etNomorTelefon = findViewById(R.id.etNotelpResE);
        etKoordinat = findViewById(R.id.etGoogleMapsResE);
        etEmail = findViewById(R.id.etEmailResE);
        btnSimpann = findViewById(R.id.btnKirimResE);
        tvId = findViewById(R.id.tvIdResE);
        tvNon = findViewById(R.id.tvNonAktifres1);

        etNama.setText(namaRestoran);
        etGambar.setText(thumbRestoran);
        etDeskripsi.setText(deskripsiRestoran);
        etAlamat.setText(alamatRestoran);
        etNomorTelefon.setText(notelpRestoran);
        etKoordinat.setText(lokasiRestoran);
        etEmail.setText(emailRestoran);
        tvId.setText(idRestoran);
        tvNon.setText("1");

        btnSimpann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubahData();
            }
        });
    }

    private void ubahData() {
        final String nama_restoran = this.etNama.getText().toString().trim();
        final String deskripsi_restoran = this.etDeskripsi.getText().toString().trim();
        final String lokasi_restoran = this.etKoordinat.getText().toString().trim();
        final String thumb_restoran = this.etGambar.getText().toString().trim();
        final String nomor_telfon_restoran = this.etNomorTelefon.getText().toString().trim();
        final String alamat_restoran = this.etAlamat.getText().toString().trim();
        final String email_restoran = this.etEmail.getText().toString().trim();
        final String nonaktifkan_restoran = this.tvNon.getText().toString().trim();
        final String id = this.tvId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UbahRestoran,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(UbahRestoranActivity.this, "Masuk pak eko", Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(UbahRestoranActivity.this, "Data Berhasil Di Ubah", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(UbahRestoranActivity.this, "gagal pak eko", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UbahRestoranActivity.this, "Gagal Mengubah Data" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UbahRestoranActivity.this, "Cek Kembali" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("nama_restoran", nama_restoran);
                params.put("deskripsi_restoran", deskripsi_restoran);
                params.put("lokasi_restoran", lokasi_restoran);
                params.put("thumb_restoran", thumb_restoran);
                params.put("nomor_telfon_restoran", nomor_telfon_restoran);
                params.put("alamat_restoran", alamat_restoran);
                params.put("email_restoran", email_restoran);
                params.put("nonaktifkan_restoran", nonaktifkan_restoran);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
