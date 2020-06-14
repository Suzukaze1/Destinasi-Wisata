package com.alvinmd.androidrplbo.activity.admin.religi;

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

public class UbahReligiActivity extends AppCompatActivity {

    EditText etNama, etGambar, etDeskripsi, etAlamat, etNomorTelefon, etKoordinat, etEmail;
    TextView tvId, tvNon;
    Button btnSimpann;

    private final String URL_UbahReligi = "https://suzukaze.000webhostapp.com/UbahWisataReligi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_religi);

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

        etNama = findViewById(R.id.etNamaWisataRE);
        etGambar = findViewById(R.id.etGambarWisataRE);
        etDeskripsi = findViewById(R.id.etDeskripsiRE);
        etAlamat = findViewById(R.id.etAlamatRE);
        etNomorTelefon = findViewById(R.id.etNotelpRE);
        etKoordinat = findViewById(R.id.etGoogleMapsRE);
        etEmail = findViewById(R.id.etEmailRE);
        btnSimpann = findViewById(R.id.btnKirimRE);
        tvId = findViewById(R.id.tvIdRE);
        tvNon = findViewById(R.id.tvNonAktif1);

        etNama.setText(namaWisata);
        etGambar.setText(thumbWisata);
        etDeskripsi.setText(deskripsiWisata);
        etAlamat.setText(alamatWisata);
        etNomorTelefon.setText(notelpWisata);
        etKoordinat.setText(lokasiWisata);
        etEmail.setText(emailWisata);
        tvId.setText(idwisata);
        tvNon.setText("1");

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
        final String nonaktifkan_wisata = this.tvNon.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UbahReligi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(UbahReligiActivity.this, "Masuk pak eko", Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(UbahReligiActivity.this, "Data Berhasil Di Ubah", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(UbahReligiActivity.this, "gagal pak eko", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UbahReligiActivity.this, "Gagal Mengubah Data" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UbahReligiActivity.this, "Cek Kembali" + error.toString(), Toast.LENGTH_SHORT).show();
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
                params.put("nonaktifkan_wisata", nonaktifkan_wisata);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
