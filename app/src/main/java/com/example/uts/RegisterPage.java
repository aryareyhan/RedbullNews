package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    EditText usernameEdt, passwordEdt, confirmPasswordEdt;
    Button registerBtn;
    SharedPreferences sp;

    private boolean isEmpty(){
        return usernameEdt.getText().toString().matches("")
                || passwordEdt.getText().toString().matches("")
                || confirmPasswordEdt.getText().toString().matches("");
    }

    private boolean nameCheck(){
        return usernameEdt.getText().toString().length() < 3;
    }

    private boolean confirmPasswordCheck(){
        return !confirmPasswordEdt.getText().toString().equals(passwordEdt.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        usernameEdt = findViewById(R.id.registerUsername);
        passwordEdt = findViewById(R.id.registerPassword);
        confirmPasswordEdt = findViewById(R.id.registerConfirmPassword);
        registerBtn = findViewById(R.id.registerBtn);

        sp = getSharedPreferences("UserInfo",MODE_PRIVATE);

        registerBtn.setOnClickListener(view -> {
            if(isEmpty()){
                Toast.makeText(this, "Please fill form!", Toast.LENGTH_SHORT).show();
            }
            else if(nameCheck()){
                Toast.makeText(this, "Username must be at least 3 letters!", Toast.LENGTH_SHORT).show();
            }
            else if(confirmPasswordCheck()){
                Toast.makeText(this, "Passwords must be the same!", Toast.LENGTH_SHORT).show();
            }
            else {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(usernameEdt.getText().toString(),usernameEdt.getText().toString());
                editor.putString(usernameEdt.getText().toString()+"pass",passwordEdt.getText().toString());
                editor.apply();

                Toast.makeText(this,"Register Success",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}