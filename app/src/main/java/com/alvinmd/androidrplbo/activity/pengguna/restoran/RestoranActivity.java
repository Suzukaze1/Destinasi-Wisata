package com.alvinmd.androidrplbo.activity.pengguna.restoran;

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
import android.widget.Toast;

import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.adapters.pengguna.RVRestoran;
import com.alvinmd.androidrplbo.model.Restoran;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestoranActivity extends AppCompatActivity {

    RecyclerView rvRestoran;
    private JsonArrayRequest request;
    private List<Restoran> listRestoran;
    private RVRestoran adapter;
    SwipeRefreshLayout ref01;

    private final String URL_ViewRestoran= "https://suzukaze.000webhostapp.com/Restoran.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran);

        listRestoran = new ArrayList<>();
        ref01 = findViewById(R.id.refresh1Restoran);

        rvRestoran = findViewById(R.id.rvRestoran);
        rvRestoran.setHasFixedSize(true);
        rvRestoran.setLayoutManager(new LinearLayoutManager(this));
        rvRestoran.setAdapter(adapter);
        adapter = new RVRestoran(listRestoran);

        ref01.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ref01.setRefreshing(false);
                        finish();
                        Intent x = new Intent(RestoranActivity.this, RestoranActivity.class);
                        startActivity(x);
                    }
                }, 2000);
            }
        });

        panggilRestoran();
    }

    private void panggilRestoran() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ViewRestoran,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++){
                                JSONObject restoran = array.getJSONObject(i);
                                listRestoran.add(new Restoran(
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
                            RVRestoran rvK = new RVRestoran(RestoranActivity.this, listRestoran);
                            rvRestoran.setAdapter(rvK);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RestoranActivity.this, "Database Tidak Terbaca", Toast.LENGTH_SHORT).show();
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
