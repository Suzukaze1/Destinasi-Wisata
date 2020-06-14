package com.alvinmd.androidrplbo.activity.admin.homestay;

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

public class TambahHomestayActivity extends AppCompatActivity {

    EditText etNWisata, etGWisata, etDWisata, etAWisata, etNTWisata, etKoordinatWisata, etEmWisata;
    TextView tvIdN;
    Button btnSend;

    private final String URL_TambahHomestay= "https://suzukaze.000webhostapp.com/TambahHomestay.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_homestay);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        etNWisata = findViewById(R.id.etNamaHomestay001);
        etGWisata = findViewById(R.id.etGambarHomestay001);
        etDWisata = findViewById(R.id.etDeskripsiH001);
        etAWisata = findViewById(R.id.etAlamatH001);
        etNTWisata = findViewById(R.id.etNotelpH001);
        etKoordinatWisata = findViewById(R.id.etGoogleMapsH001);
        etEmWisata = findViewById(R.id.etEmailH001);
        btnSend = findViewById(R.id.btnKirimH001);

        tvIdN = findViewById(R.id.tvIDNon001);
        tvIdN.setText("1");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahHomestay();
            }
        });
    }

    private void tambahHomestay() {
        final String nama_homestay = this.etNWisata.getText().toString().trim();
        final String deskripsi_homestay = this.etDWisata.getText().toString().trim();
        final String lokasi_homestay = this.etKoordinatWisata.getText().toString().trim();
        final String thumb_homestay = this.etGWisata.getText().toString().trim();
        final String nomor_telfon_homestay = this.etNTWisata.getText().toString().trim();
        final String alamat_homestay = this.etAWisata.getText().toString().trim();
        final String email_homestay = this.etEmWisata.getText().toString().trim();
        final String nonaktifkan_wisata = this.tvIdN.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TambahHomestay,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(TambahHomestayActivity.this, "Data Berhasil Di Simpan", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahHomestayActivity.this, "Gagal Menambahkan Data" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahHomestayActivity.this, "Cek Kembali" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama_homestay", nama_homestay);
                params.put("deskripsi_homestay", deskripsi_homestay);
                params.put("lokasi_homestay", lokasi_homestay);
                params.put("thumb_homestay", thumb_homestay);
                params.put("nomor_telfon_homestay", nomor_telfon_homestay);
                params.put("alamat_homestay", alamat_homestay);
                params.put("email_homestay", email_homestay);
                params.put("nonaktifkan_homestay", nonaktifkan_wisata);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
