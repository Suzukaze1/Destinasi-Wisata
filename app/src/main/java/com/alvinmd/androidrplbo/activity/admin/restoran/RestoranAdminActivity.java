package com.alvinmd.androidrplbo.activity.admin.restoran;

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
import com.alvinmd.androidrplbo.activity.admin.religi.ReligiAdminActivity;
import com.alvinmd.androidrplbo.adapters.admin.RVRestoranAdmin;
import com.alvinmd.androidrplbo.model.Restoran;
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

public class RestoranAdminActivity extends AppCompatActivity {

    RecyclerView rvHomestay;
    private JsonArrayRequest request;
    private List<Restoran> listHomestay;
    private RVRestoranAdmin adapter;
    FloatingActionButton fabTambah;
    SwipeRefreshLayout ref01;

    private final String URL_ViewRestoran= "https://suzukaze.000webhostapp.com/Restoran.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran_admin);

        listHomestay = new ArrayList<>();

        fabTambah = findViewById(R.id.floatTambahR);
        ref01 = findViewById(R.id.refresh0101);

        rvHomestay = findViewById(R.id.rvRestoranAdm);
        rvHomestay.setHasFixedSize(true);
        rvHomestay.setLayoutManager(new LinearLayoutManager(this));
        rvHomestay.setAdapter(adapter);
        adapter = new RVRestoranAdmin(listHomestay);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RestoranAdminActivity.this, TambahRestoranActivity.class);
                startActivity(i);
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
                        Intent x = new Intent(RestoranAdminActivity.this, RestoranAdminActivity.class);
                        startActivity(x);
                    }
                }, 2000);
            }
        });

        panggilHomestay();
    }

    private void panggilHomestay() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ViewRestoran,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++){
                                JSONObject restoran = array.getJSONObject(i);
                                listHomestay.add(new Restoran(
                                        restoran.getString("nama_restoran"),
                                        restoran.getString("deskripsi_restoran"),
                                        restoran.getString("thumb_restoran"),
                                        restoran.getString("lokasi_restoran"),
                                        restoran.getString("nomor_telfon_restoran"),
                                        restoran.getString("alamat_restoran"),
                                        restoran.getString("email_restoran"),
                                        restoran.getString("nonaktifkan_restoran"),
                                        restoran.getString("id")
                                ));
                            }
                            RVRestoranAdmin rvH = new RVRestoranAdmin(RestoranAdminActivity.this, listHomestay);
                            rvHomestay.setAdapter(rvH);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RestoranAdminActivity.this, "Database Tidak Terbaca", Toast.LENGTH_SHORT).show();
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
