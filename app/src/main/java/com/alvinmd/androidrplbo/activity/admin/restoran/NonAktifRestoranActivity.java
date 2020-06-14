package com.alvinmd.androidrplbo.activity.admin.restoran;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class NonAktifRestoranActivity extends AppCompatActivity {

    Button btnIya, btnTidak;
    TextView tvid, tvnonid;

    private final String URL_NonAktifRestoran = "https://suzukaze.000webhostapp.com/NonAktifRestoran.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_aktif_restoran);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        String idWisata = getIntent().getExtras().getString("id");
        String nonAktifWisata = getIntent().getExtras().getString("nonaktif");

        btnIya = findViewById(R.id.btnIya04);
        btnTidak = findViewById(R.id.btnTidak04);

        tvid = findViewById(R.id.tvIdN003);
        tvnonid = findViewById(R.id.tvIdNon003);

        String data = "0";

        tvnonid.setText(data);
        tvid.setText(idWisata);

        btnIya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonAktifRestoran();
            }
        });

        btnTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void nonAktifRestoran() {
        final String nonaktifkan_restoran = this.tvnonid.getText().toString().trim();
        final String id = this.tvid.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_NonAktifRestoran,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(NonAktifRestoranActivity.this, "Masuk pak eko", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(NonAktifRestoranActivity.this, "Data Berhasil Di Ubah", Toast.LENGTH_SHORT).show();
                                finish();

                            }else{
                                Toast.makeText(NonAktifRestoranActivity.this, "gagal pak eko", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(NonAktifRestoranActivity.this, "Gagal Mengubah Data" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NonAktifRestoranActivity.this, "Cek Kembali" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("nonaktifkan_restoran", nonaktifkan_restoran);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
