package com.jramons.examenandroid.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.jramons.examenandroid.R;
import com.jramons.examenandroid.ViewModel.LoginViewModel;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout etEmail,etPassword;
    private Button btnSignIn;
    private TextView tvSignUp;
    private String email,password;
    private LoginViewModel viewModel;
    private SignUp signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this, SecondActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        initUI();
        btnSignIn.setOnClickListener(v -> {
            validateData();
        });

        tvSignUp.setOnClickListener(v -> {
            if(signUp == null){
                signUp = new SignUp();
            }
            signUp.show(getSupportFragmentManager(),"SignUp");
        });
    }

    private void validateData() {
        etEmail.setError(null);
        etPassword.setError(null);
        email = etEmail.getEditText().getText().toString();
        password = etPassword.getEditText().getText().toString();

        if(email.isEmpty()){
            etEmail.setError("Email vacio");
        }else if(password.isEmpty()){
            etPassword.setError("Password Vacio");
        }else{
            signInAuthentication(email,password);
        }
    }

    private void signInAuthentication(String email, String password) {
        viewModel.signIn(email,password).observe(this, signIn->{
            if(signIn){
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void initUI() {
        etEmail = findViewById(R.id.singInETUser);
        etPassword = findViewById(R.id.singInEtpassword);
        btnSignIn = findViewById(R.id.singInBtn);
        tvSignUp = findViewById(R.id.singIn_textViewSingUp);
    }
}