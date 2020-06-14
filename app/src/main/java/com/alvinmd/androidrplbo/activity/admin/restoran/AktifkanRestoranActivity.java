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

public class AktifkanRestoranActivity extends AppCompatActivity {

    Button btn1, btn2;
    TextView tv1,tv2;

    private final String Aktifkan_Restoran = "https://suzukaze.000webhostapp.com/NonAktifRestoran.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktifkan_restoran);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        String idWisata = getIntent().getExtras().getString("id");
        String nonAktifWisata = getIntent().getExtras().getString("nonaktif");

        btn1 = findViewById(R.id.btnIyaAktif019);
        btn2 = findViewById(R.id.btnTidakAktifkan019);

        tv1 = findViewById(R.id.tvIdAA009);
        tv2 = findViewById(R.id.tvIdAktif009);

        String data = "1";

        tv2.setText(data);
        tv1.setText(idWisata);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aktifkanRestoran();
            }
        });
    }

    private void aktifkanRestoran() {
        final String nonaktifkan_restoran = this.tv2.getText().toString().trim();
        final String id = this.tv1.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Aktifkan_Restoran,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AktifkanRestoranActivity.this, "Masuk pak eko", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(AktifkanRestoranActivity.this, "Data Berhasil Di Ubah", Toast.LENGTH_SHORT).show();
                                finish();

                            }else{
                                Toast.makeText(AktifkanRestoranActivity.this, "gagal pak eko", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AktifkanRestoranActivity.this, "Gagal Mengubah Data" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AktifkanRestoranActivity.this, "Cek Kembali" + error.toString(), Toast.LENGTH_SHORT).show();
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
