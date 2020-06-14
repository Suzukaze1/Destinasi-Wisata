package com.alvinmd.androidrplbo.activity.pengguna.homestay;

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
import com.alvinmd.androidrplbo.adapters.pengguna.RVHomestay;
import com.alvinmd.androidrplbo.model.Homestay;
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

public class HomestayActivity extends AppCompatActivity {

    RecyclerView rvHomestay;
    private List<Homestay> listHomestay;
    private RVHomestay adapter;
    SwipeRefreshLayout ref01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homestay);

        listHomestay = new ArrayList<>();
        ref01 = findViewById(R.id.refresh1Homestay);

        rvHomestay = findViewById(R.id.rvHomestay1);
        rvHomestay.setHasFixedSize(true);
        rvHomestay.setLayoutManager(new LinearLayoutManager(this));
        rvHomestay.setAdapter(adapter);
        adapter = new RVHomestay(listHomestay);

        ref01.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ref01.setRefreshing(false);
                        finish();
                        Intent x = new Intent(HomestayActivity.this, HomestayActivity.class);
                        startActivity(x);
                    }
                }, 2000);
            }
        });

        panggilHomestay();
    }

    private void panggilHomestay() {
        String URL_ViewHomestay = "https://suzukaze.000webhostapp.com/Homestay.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ViewHomestay,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++){
                                JSONObject homestay = array.getJSONObject(i);
                                listHomestay.add(new Homestay(
                                        homestay.getString("nama_homestay"),
                                        homestay.getString("deskripsi_homestay"),
                                        homestay.getString("thumb_homestay"),
                                        homestay.getString("lokasi_homestay"),
                                        homestay.getString("nomor_telfon_homestay"),
                                        homestay.getString("alamat_homestay"),
                                        homestay.getString("email_homestay"),
                                        homestay.getString("nonaktifkan_homestay"),
                                        homestay.getString("id")
                                ));
                            }
                            RVHomestay rvH = new RVHomestay(HomestayActivity.this, listHomestay);
                            rvHomestay.setAdapter(rvH);


                        } catch (JSONException e) {
                            Toast.makeText(HomestayActivity.this, "Pastikan Data sudah benar", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomestayActivity.this, "Database Tidak Terbaca", Toast.LENGTH_SHORT).show();
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
