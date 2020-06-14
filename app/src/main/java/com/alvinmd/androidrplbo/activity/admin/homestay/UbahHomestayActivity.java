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

public class UbahHomestayActivity extends AppCompatActivity {

    EditText etNama, etGambar, etDeskripsi, etAlamat, etNomorTelefon, etKoordinat, etEmail;
    TextView tvId, tvNon;
    Button btnSimpann;

    private final String URL_UbahHomestay = "https://suzukaze.000webhostapp.com/UbahHomestay.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_homestay);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //menerima data
        String namaHomestay = getIntent().getExtras().getString("tvHomestay");
        String deskripsiHomestay = getIntent().getExtras().getString("tvDeskripsi");
        String thumbHomestay = getIntent().getExtras().getString("imgThumb");
        final String lokasiHomestay = getIntent().getExtras().getString("tvNavigasii");
        String notelpHomestay = getIntent().getExtras().getString("notelp");
        String alamatHomestay = getIntent().getExtras().getString("alamat");
        String emailHomestay = getIntent().getExtras().getString("email");
        String idHomestay = getIntent().getExtras().getString("id");

        etNama = findViewById(R.id.etNamaWisataHE);
        etGambar = findViewById(R.id.etGambarWisataHE);
        etDeskripsi = findViewById(R.id.etDeskripsiHE);
        etAlamat = findViewById(R.id.etAlamatHE);
        etNomorTelefon = findViewById(R.id.etNotelpHE);
        etKoordinat = findViewById(R.id.etGoogleMapsHE);
        etEmail = findViewById(R.id.etEmailHE);
        btnSimpann = findViewById(R.id.btnKirimHE);
        tvId = findViewById(R.id.tvIdHE);
        tvNon = findViewById(R.id.tvNonAktif2);

        etNama.setText(namaHomestay);
        etGambar.setText(thumbHomestay);
        etDeskripsi.setText(deskripsiHomestay);
        etAlamat.setText(alamatHomestay);
        etNomorTelefon.setText(notelpHomestay);
        etKoordinat.setText(lokasiHomestay);
        etEmail.setText(emailHomestay);
        tvId.setText(idHomestay);
        tvNon.setText("1");

        btnSimpann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubahData();
            }
        });
    }

    private void ubahData() {
        final String nama_homestay = this.etNama.getText().toString().trim();
        final String deskripsi_homestay = this.etDeskripsi.getText().toString().trim();
        final String lokasi_homestay = this.etKoordinat.getText().toString().trim();
        final String thumb_homestay = this.etGambar.getText().toString().trim();
        final String nomor_telfon_homestay = this.etNomorTelefon.getText().toString().trim();
        final String alamat_homestay = this.etAlamat.getText().toString().trim();
        final String email_homestay = this.etEmail.getText().toString().trim();
        final String nonaktifkan_homestay = this.tvNon.getText().toString().trim();
        final String id = this.tvId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UbahHomestay,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(UbahHomestayActivity.this, "Masuk pak eko", Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(UbahHomestayActivity.this, "Data Berhasil Di Ubah", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(UbahHomestayActivity.this, "gagal pak eko", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UbahHomestayActivity.this, "Gagal Mengubah Data" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UbahHomestayActivity.this, "Cek Kembali" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("nama_homestay", nama_homestay);
                params.put("deskripsi_homestay", deskripsi_homestay);
                params.put("lokasi_homestay", lokasi_homestay);
                params.put("thumb_homestay", thumb_homestay);
                params.put("nomor_telfon_homestay", nomor_telfon_homestay);
                params.put("alamat_homestay", alamat_homestay);
                params.put("email_homestay", email_homestay);
                params.put("nonaktifkan_homestay", nonaktifkan_homestay);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
