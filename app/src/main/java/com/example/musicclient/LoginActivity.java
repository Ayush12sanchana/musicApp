package com.example.musicclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    public EditText email,password;
    public Button loginBtn;
    public TextView registerBtn;
    public FirebaseAuth mFirebaseAuth;
    public String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.btn_login);
        registerBtn = findViewById(R.id.btn_register);

        mFirebaseAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /////
                if(email.getText().toString().isEmpty()){
                    email.setError("Please enter email id");
                    email.requestFocus();
                }
                else  if(password.getText().toString().isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.getText().toString().isEmpty() && password.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.getText().toString().isEmpty() && password.getText().toString().isEmpty())) {

                    mFirebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                                if (mFirebaseUser != null) {

                                    userID = mFirebaseUser.getUid();

                                    Bundle bundle = new Bundle();
                                    bundle.putString("uid", userID);


                                    Intent intent = new Intent(LoginActivity.this, OnlineMusicMenu.class);
                                    //intent.putExtras(bundle);
                                    startActivity(intent);

                                }


                            }
                        }
                    });


                }




                /////

            }
        });
    }
}
