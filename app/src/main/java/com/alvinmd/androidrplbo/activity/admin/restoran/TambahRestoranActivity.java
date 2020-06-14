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

public class TambahRestoranActivity extends AppCompatActivity {

    EditText etNRestoran, etGRestoran, etDRestoran, etARestoran, etNTRestoran, etKoordinatRestoran, etEmRestoran;
    TextView tvIdN;
    Button btnSend;

    private final String URL_TambahRestoran= "https://suzukaze.000webhostapp.com/TambahRestoran.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_restoran);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        etNRestoran = findViewById(R.id.etNamaRestoran001);
        etGRestoran = findViewById(R.id.etGambarRestoran001);
        etDRestoran = findViewById(R.id.etDeskripsiR0001);
        etARestoran = findViewById(R.id.etAlamatR0001);
        etNTRestoran = findViewById(R.id.etNotelpR0001);
        etKoordinatRestoran = findViewById(R.id.etGoogleMapsR0001);
        etEmRestoran = findViewById(R.id.etEmailR0001);
        btnSend = findViewById(R.id.btnKirimR001);

        tvIdN = findViewById(R.id.tvIDNon00001);
        tvIdN.setText("1");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahRestoran();
            }
        });
    }

    private void tambahRestoran() {
        final String nama_restoran = this.etNRestoran.getText().toString().trim();
        final String deskripsi_restoran = this.etDRestoran.getText().toString().trim();
        final String lokasi_restoran = this.etKoordinatRestoran.getText().toString().trim();
        final String thumb_restoran = this.etGRestoran.getText().toString().trim();
        final String nomor_telfon_restoran = this.etNTRestoran.getText().toString().trim();
        final String alamat_restoran = this.etARestoran.getText().toString().trim();
        final String email_restoran = this.etEmRestoran.getText().toString().trim();
        final String nonaktifkan_restoran = this.tvIdN.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TambahRestoran,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(TambahRestoranActivity.this, "Data Berhasil Di Simpan", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahRestoranActivity.this, "Gagal Menambahkan Data" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahRestoranActivity.this, "Cek Kembali" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
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
