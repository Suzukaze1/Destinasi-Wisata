package com.alvinmd.androidrplbo.activity.admin.konvensional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.adapters.admin.RVKonvensionalAdmin;
import com.alvinmd.androidrplbo.model.WisataKonvensional;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KonvensionalAdminActivity extends AppCompatActivity {

    RecyclerView rvKonvensional;
    private JsonArrayRequest request;
    private List<WisataKonvensional> listKonvensional;
    FloatingActionButton fabTambah;
    private RVKonvensionalAdmin adapter;
    SwipeRefreshLayout ref01;

    private final String Panggil_Konvensional= "https://suzukaze.000webhostapp.com/WisataKonvesional.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konvensional_admin);

        listKonvensional = new ArrayList<>();

        fabTambah = findViewById(R.id.floatTambah);
        ref01 = findViewById(R.id.refresh01);


        rvKonvensional = findViewById(R.id.rvKonvensional);
        rvKonvensional.setHasFixedSize(true);
        rvKonvensional.setLayoutManager(new LinearLayoutManager(this));
        rvKonvensional.setAdapter(adapter);
        adapter = new RVKonvensionalAdmin(listKonvensional);
        panggilKonvensional();

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(KonvensionalAdminActivity.this, TambahWisataKonvensionalActivity.class);
                startActivity(a);
            }
        });

        ref01.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ref01.setRefreshing(false);
                        finish();
                        Intent x = new Intent(KonvensionalAdminActivity.this, KonvensionalAdminActivity.class);
                        startActivity(x);
                    }
                }, 2000);
            }
        });
    }

    private void panggilKonvensional() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Panggil_Konvensional,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++){
                                JSONObject konvensional = array.getJSONObject(i);
                                listKonvensional.add(new WisataKonvensional(
                                        konvensional.getString("nama_wisata"),
                                        konvensional.getString("deskripsi_wisata"),
                                        konvensional.getString("thumb_wisata"),
                                        konvensional.getString("lokasi_wisata"),
                                        konvensional.getString("nomor_telfon_wisata"),
                                        konvensional.getString("alamat_wisata"),
                                        konvensional.getString("email_wisata"),
                                        konvensional.getString("id"),
                                        konvensional.getString("nonaktifkan_wisata")
                                ));
                            }
                            RVKonvensionalAdmin rvK = new RVKonvensionalAdmin(KonvensionalAdminActivity.this, listKonvensional);
                            rvKonvensional.setAdapter(rvK);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KonvensionalAdminActivity.this, "Database Tidak Terbaca", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
