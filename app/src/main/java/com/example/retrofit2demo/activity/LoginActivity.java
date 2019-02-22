package com.example.retrofit2demo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.retrofit2demo.R;
import com.example.retrofit2demo.model.LoginData;
import com.example.retrofit2demo.model.LoginResponse;
import com.example.retrofit2demo.network.API;
import com.example.retrofit2demo.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtUsername, edtPassword;
    private Button btnLogin;
    private Retrofit retrofit;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);
        retrofit = API.getRetrofitInstance();
        apiInterface = retrofit.create(ApiInterface.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading");
                progressDialog.show();
                String strUname = edtUsername.getText().toString().trim();
                String strPsswd = edtPassword.getText().toString().trim();
                Call<LoginResponse> call = apiInterface.getLogin(new LoginData(strUname, strPsswd));
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this,
                                "Success: " + loginResponse.getSuccess() + "\n" + loginResponse.getErrorMessage(),
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, ProjectActivity.class));
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });

    }
}
