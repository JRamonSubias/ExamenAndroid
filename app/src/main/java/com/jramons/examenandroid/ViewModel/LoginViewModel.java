package com.jramons.examenandroid.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jramons.examenandroid.Model.AuthProvider;

public class LoginViewModel extends ViewModel {
    private AuthProvider mAuth;
    private MutableLiveData<Boolean> checkSignUp;
    private MutableLiveData<Boolean> checkSignIn;

    public LoginViewModel(){
        mAuth = AuthProvider.getInstance();
    }

    public MutableLiveData<Boolean> registerUser(String email, String password){
        if(checkSignUp == null){
            checkSignUp = new MutableLiveData<>();
        }
        mAuth.SignUp(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                checkSignUp.setValue(true);
            }else{
                checkSignUp.setValue(false);
            }
        });
        return checkSignUp;
    }

    public MutableLiveData<Boolean> signIn(String email,String password){
        if(checkSignIn == null){
            checkSignIn = new MutableLiveData<>();
        }
        mAuth.SignIn(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                checkSignIn.setValue(true);
            }else {
                checkSignIn.setValue(false);
            }
        });
        return checkSignIn;
    }

}
