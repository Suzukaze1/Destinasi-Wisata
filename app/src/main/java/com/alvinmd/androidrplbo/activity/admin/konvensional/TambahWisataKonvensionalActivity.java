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

public class TambahWisataKonvensionalActivity extends AppCompatActivity {

    EditText etNWisata, etGWisata, etDWisata, etAWisata, etNTWisata, etKoordinatWisata, etEmWisata;
    TextView tv01;
    Button btnSend;

    private final String TambahKonvensional= "https://suzukaze.000webhostapp.com/TambahWisataKonvensional.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_wisata_konvensional);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        etNWisata = findViewById(R.id.etNamaWisataK);
        etGWisata = findViewById(R.id.etGambarWisataK);
        etDWisata = findViewById(R.id.etDeskripsiK);
        etAWisata = findViewById(R.id.etAlamatK);
        etNTWisata = findViewById(R.id.etNotelpK);
        etKoordinatWisata = findViewById(R.id.etGoogleMapsK);
        etEmWisata = findViewById(R.id.etEmailK);
        btnSend = findViewById(R.id.btnKirimK);

        tv01 = findViewById(R.id.tvidnonK);
        tv01.setText("1");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahWisataKonvensional();
            }
        });
    }

    private void tambahWisataKonvensional() {
        final String nama_wisata = this.etNWisata.getText().toString().trim();
        final String deskripsi_wisata = this.etDWisata.getText().toString().trim();
        final String lokasi_wisata = this.etKoordinatWisata.getText().toString().trim();
        final String thumb_wisata = this.etGWisata.getText().toString().trim();
        final String nomor_telfon_wisata = this.etNTWisata.getText().toString().trim();
        final String alamat_wisata = this.etAWisata.getText().toString().trim();
        final String email_wisata = this.etEmWisata.getText().toString().trim();
        final String nonaktifkan_wisata = this.tv01.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, TambahKonvensional,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(TambahWisataKonvensionalActivity.this, "Data Berhasil Di Simpan", Toast.LENGTH_SHORT).show();
                                onRestart();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahWisataKonvensionalActivity.this, "Gagal Menambahkan Data" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahWisataKonvensionalActivity.this, "Cek Kembali" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama_wisata", nama_wisata);
                params.put("deskripsi_wisata", deskripsi_wisata);
                params.put("lokasi_wisata", lokasi_wisata);
                params.put("thumb_wisata", thumb_wisata);
                params.put("nomor_telfon_wisata", nomor_telfon_wisata);
                params.put("alamat_wisata", alamat_wisata);
                params.put("email_wisata", email_wisata);
                params.put("nonaktifkan_wisata", nonaktifkan_wisata);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
