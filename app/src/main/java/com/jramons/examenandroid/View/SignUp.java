package com.jramons.examenandroid.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.jramons.examenandroid.R;
import com.jramons.examenandroid.ViewModel.LoginViewModel;

public class SignUp extends DialogFragment {
    
    private TextInputLayout etEmail,etPasword,etConfirmPassword;
    private Button btnSignUp;
    private String email, password,confirmPassword;
    private LoginViewModel viewModel;
    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        initUI(view);
        btnSignUp.setOnClickListener(v -> {
            validateData();
        });

        return view; 
    }

    private void validateData() {
        etEmail.setError(null);
        etPasword.setError(null);
        etConfirmPassword.setError(null);

        email= etEmail.getEditText().getText().toString();
        password = etPasword.getEditText().getText().toString();
        confirmPassword = etConfirmPassword.getEditText().getText().toString();

        if (email.isEmpty()) {
            etEmail.setError("Ingrese Usuario");
        } else if (password.isEmpty()) {
            etPasword.setError("Ingrese Contraseña");
        } else if (password.length() < 6) {
            etPasword.setError("Minimo 6 caracteres en la contraseña");
        } else if (!password.equals(confirmPassword)){
            etConfirmPassword.setError("Contraseñas no coinciden");
        }else{
            registerAssistant(email,password);
        }
    }

    private void registerAssistant(String email, String password) {
        viewModel.registerUser(email,password).observe(getActivity(), signUp ->{
            if(signUp){
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else {
                Toast.makeText(getContext(), "Hubo un problema verifique sus datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI(View view) {
        etEmail = view.findViewById(R.id.singUpETEmail);
        etPasword = view.findViewById(R.id.singUpEtpassword);
        etConfirmPassword = view.findViewById(R.id.singUpEtConfirmPassword);
        btnSignUp = view.findViewById(R.id.SignUp_btnSign);
    }
}