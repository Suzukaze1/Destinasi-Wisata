package com.alvinmd.androidrplbo.activity.admin.religi;

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
import com.alvinmd.androidrplbo.activity.admin.homestay.HomestayAdminActivity;
import com.alvinmd.androidrplbo.adapters.admin.RVReligiAdmin;
import com.alvinmd.androidrplbo.model.WisataReligi;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReligiAdminActivity extends AppCompatActivity {

    RecyclerView rvReligi;
    private List<WisataReligi> listReligi;
    SwipeRefreshLayout ref01;
    private RVReligiAdmin adapter;
    FloatingActionButton flo01;

    private final String Panggil_Religi= "https://suzukaze.000webhostapp.com/WisataReligi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religi_admin);

        listReligi = new ArrayList<>();
        ref01 = findViewById(R.id.refresh00001);
        flo01 = findViewById(R.id.floatTambahR);

        flo01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(ReligiAdminActivity.this, TambahWisataReligiActivity.class);
                startActivity(k);
            }
        });

        rvReligi = findViewById(R.id.rvReligiAdmin);
        rvReligi.setHasFixedSize(true);
        rvReligi.setLayoutManager(new LinearLayoutManager(this));
        rvReligi.setAdapter(adapter);
        adapter = new RVReligiAdmin(listReligi);

        ref01.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ref01.setRefreshing(false);
                        finish();
                        Intent x = new Intent(ReligiAdminActivity.this, ReligiAdminActivity.class);
                        startActivity(x);
                    }
                }, 2000);
            }
        });

        panggilReligi();
    }

    private void panggilReligi() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Panggil_Religi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject religi = array.getJSONObject(i);
                                listReligi.add(new WisataReligi(
                                        religi.getString("nama_wisata"),
                                        religi.getString("deskripsi_wisata"),
                                        religi.getString("thumb_wisata"),
                                        religi.getString("lokasi_wisata"),
                                        religi.getString("nomor_telfon_wisata"),
                                        religi.getString("alamat_wisata"),
                                        religi.getString("email_wisata"),
                                        religi.getString("nonaktifkan_wisata"),
                                        religi.getString("id")
                                ));
                            }
                            RVReligiAdmin rvK = new RVReligiAdmin(ReligiAdminActivity.this, listReligi);
                            rvReligi.setAdapter(rvK);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ReligiAdminActivity.this, "Database Tidak Terbaca", Toast.LENGTH_SHORT).show();
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
