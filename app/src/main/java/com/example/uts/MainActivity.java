package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText usernameEdt, passwordEdt;
    Button loginBtn, registerBtn;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEdt = findViewById(R.id.loginUsernameEdt);
        passwordEdt = findViewById(R.id.loginPasswordEdt);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.loginRegisterBtn);

        sp = getSharedPreferences("UserInfo", MODE_PRIVATE);

        Map<String, ?> allEntries = sp.getAll();
        if (allEntries.isEmpty()) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("mae","mae");
            editor.putString("mae"+"pass","123");
            editor.apply();
        }

        loginBtn.setOnClickListener(view -> {
            if (usernameEdt.getText().toString().equals(sp.getString(usernameEdt.getText().toString(),""))
                    && passwordEdt.getText().toString().equals(sp.getString(usernameEdt.getText().toString()+"pass",""))){
                Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, NewsPage.class);
                intent.putExtra("loggedIn",usernameEdt.getText().toString());
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show();
            }
        });

        registerBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterPage.class);
            startActivity(intent);
        });
    }
}