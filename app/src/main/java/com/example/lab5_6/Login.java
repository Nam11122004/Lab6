package com.example.lab5_6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.lab5_6.Model.Response_Model;
import com.example.lab5_6.Model.User;
import com.example.lab5_6.services.HttpRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
EditText txtuser,txtpass;
TextView txtsignup;
Button btnlogin;
    private HttpRequest httpRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ánh xạ
        txtuser=findViewById(R.id.txtuser);
        txtpass=findViewById(R.id.txtpass);
        btnlogin=findViewById(R.id.btnlogin);
        txtsignup=findViewById(R.id.txtsignup);
        httpRequest=new HttpRequest();
        //khi click vao txtsignup de mo man hinh dang ky
        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        //xu ly khi click login
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                String username=txtuser.getText().toString().trim();
                String password=txtpass.getText().toString().trim();
                user.setUsername(username);
                user.setPassword(password);
                httpRequest.callApi().login(user).enqueue(responseUser);

            }
        });
    }
//
Callback<Response_Model<User>> responseUser = new Callback<Response_Model<User>>() {
    @Override
    public void onResponse(Call<Response_Model<User>> call, Response<Response_Model<User>> response) {
        if(response.isSuccessful()){
            if (response.body().getStatus() == 200) {
                Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                //lưu token, lưu device token, id
                SharedPreferences sharedPreferences = getSharedPreferences("INFO", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", response.body().getToken());
                editor.putString("refreshToken", response.body().getRefreshToken());
                editor.putString("id", response.body().getData().get_id());
                editor.apply();
                Log.d("Token", "onResponse: " + response.body().getToken());
                //sau khi chuyển màn hình chính
                startActivity(new Intent(Login.this, MainActivity.class));
            } else {
                Toast.makeText(Login.this, "Login fail", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<Response_Model<User>> call, Throwable t) {
        Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
    }
};


}