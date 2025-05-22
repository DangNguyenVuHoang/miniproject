package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView welcomeMassage;
    private final String ACCOUNT ="Hoang";
    private final String PASSWORK ="12345";
    private EditText etUsername;
    private EditText etPassword;
//    private TextView tvNotAccountYet;
    private Button btnSignIn;

    private final String REQUIRE ="Require";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

//        welcomeMassage = findViewById(R.id.welcomeMassage);
//        String username = getIntent().getStringExtra("username");
//        welcomeMassage.setText("Welcome, " + username + "!");
        etUsername = findViewById(R.id.etUsername);
        etPassword =  findViewById(R.id.etPassword);
//        tvNotAccountYet =  findViewById(R.id.tvNotAccountYet);
        btnSignIn =  findViewById(R.id.btnSignIn);

//        tvNotAccountYet.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);

    }
    private boolean checkInputEmty(){
        if(TextUtils.isEmpty(etUsername.getText().toString())){
            etUsername.setError(REQUIRE);
            return false;
        }
        if(TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError(REQUIRE);
            return false;
        }
        return true;

    }
    private boolean checkInputValue(String input, String expectedValue) {
        return input.equals(expectedValue);
    }

    private void signIn(){
        if(!checkInputEmty())
        {
            return;
        }
//        String inputUsername = etUsername.getText().toString();
//        if(!checkInputEmty() && !checkInputValue(inputUsername, ACCOUNT)){
//            return;
//        }

//        if(!checkInputEmty() && etUsername.getText().toString().equals(ACCOUNT) && etPassword.getText().toString().equals(PASSWORK)){
//            return;
//        }
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
//        intent.putExtra("USERNAME", username);
        startActivity(intent);
        finish();
        Toast.makeText(this, "SIGN IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSignIn) {
            signIn();
        }
}
}
