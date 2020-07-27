package com.halodoc.medical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by Supriyanto on 7/1/2020.
 */
public class LoginVerify extends AppCompatActivity {

    String verifyCodeBySystem;
    EditText verify;
    Button login;
    ProgressBar progressBar;
    String getNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif);

        verify = findViewById(R.id.verify);
        login = findViewById(R.id.login);
        progressBar = findViewById(R.id.progress);

        getNumber = getIntent().getStringExtra("phone");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codes = verify.getText().toString();
                if (codes.isEmpty() || codes.length() < 6){
                    verify.setError("Code otp tidak valid");
                    verify.setFocusable(true);
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyHandphone(getNumber);
            }
        });

    }

    private void verifyHandphone(String verify){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                verify, 60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, mCallback
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verifyCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null){
                progressBar.setVisibility(View.VISIBLE);
                verifyPhone(code);
                Log.d("atmamklk", "Atmamklk");
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(), "Gagal Verifikasi", Toast.LENGTH_LONG).show();
            Log.d("atmamee", "Atmamee");
        }
    };

    private void verifyPhone(String s){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyCodeBySystem,s);
        daftarByCredential(credential);
    }

    private void daftarByCredential(PhoneAuthCredential credential){

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginVerify.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(LoginVerify.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            Log.d("atmam", "Atmam");
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "Error Sign with credential", Toast.LENGTH_LONG).show();
                            Log.d("atmams", "Atmams");
                        }
                    }
                });
    }
}
