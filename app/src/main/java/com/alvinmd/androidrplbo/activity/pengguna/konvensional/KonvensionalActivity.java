package com.alvinmd.androidrplbo.activity.pengguna.konvensional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.adapters.pengguna.RVKonvensional;
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

public class KonvensionalActivity extends AppCompatActivity {

    RecyclerView rvKonvensional;
    private JsonArrayRequest request;
    Button ubah;
    FloatingActionButton fabTambah;
    SwipeRefreshLayout ref01;
    private RVKonvensional adapter;
    private List<WisataKonvensional> listKonvensional;


    private final String URL_ViewKonvensional= "https://suzukaze.000webhostapp.com/WisataKonvesional.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konvensional);

        listKonvensional = new ArrayList<>();
        fabTambah = findViewById(R.id.floatTambah);
        ref01 = findViewById(R.id.refresh2Konvensional);

        rvKonvensional = findViewById(R.id.rvKonvensional);
        rvKonvensional.setHasFixedSize(true);
        rvKonvensional.setLayoutManager(new LinearLayoutManager(this));
        rvKonvensional.setAdapter(adapter);

        ref01.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ref01.setRefreshing(false);
                        finish();
                        Intent x = new Intent(KonvensionalActivity.this, KonvensionalActivity.class);
                        startActivity(x);
                    }
                }, 2000);
            }
        });

        panggilKonvensional();
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        adapter = new RVKonvensional(listKonvensional);

    }

    private void panggilKonvensional() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ViewKonvensional,
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
                            RVKonvensional rvK = new RVKonvensional(KonvensionalActivity.this, listKonvensional);
                            rvKonvensional.setAdapter(rvK);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KonvensionalActivity.this, "Database Tidak Terbaca", Toast.LENGTH_SHORT).show();
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