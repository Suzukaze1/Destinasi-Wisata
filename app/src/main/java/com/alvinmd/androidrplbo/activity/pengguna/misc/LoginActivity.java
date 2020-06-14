package com.alvinmd.androidrplbo.activity.pengguna.misc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alvinmd.androidrplbo.R;
import com.alvinmd.androidrplbo.activity.pengguna.MainActivity;
import com.alvinmd.androidrplbo.activity.admin.AdminActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etUser, etPass;
    Button btnLogin, btnlogin, btnEdit;
    ProgressBar pbLogin;
    LinearLayout llAdmin;
    private static String URL_LOGIN = "https://suzukaze.000webhostapp.com/login.php";
    ImageView ivBalek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        etUser = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPassword);
        //pbLogin = findViewById(R.id.pbLogin);
        btnLogin = findViewById(R.id.btnLogin);
        ivBalek = findViewById(R.id.ivBalek);
        btnlogin = findViewById(R.id.btnlogin);

        ivBalek.setOnClickListener(this);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin){
            String mUser = etUser.getText().toString().trim();
            String mPass = etPass.getText().toString().trim();
            if (!mUser.isEmpty() || !mPass.isEmpty()){
                cekLogin(mUser, mPass);
            }else {
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void cekLogin(final String nama, final String password) {
        //pbLogin.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (succes.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nama = object.getString("nama").trim();

                                    Toast.makeText(LoginActivity.this, "Berhasil Login Sebagai " + nama, Toast.LENGTH_SHORT).show();

//                                    Intent dashboard = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(dashboard);

                                    //pbLogin.setVisibility(View.GONE);
                                    btnLogin.setVisibility(View.VISIBLE);
                                    Intent j = new Intent(LoginActivity.this, AdminActivity.class);
                                    startActivity(j);
                                    finish();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Gagal Login, Periksa Kembali Username atau Password", Toast.LENGTH_SHORT).show();
                            //pbLogin.setVisibility(View.GONE);
                            btnLogin.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Terjadi Kesalahan" + error.toString(), Toast.LENGTH_SHORT).show();
                        //pbLogin.setVisibility(View.GONE);
                        btnLogin.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama", nama);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
