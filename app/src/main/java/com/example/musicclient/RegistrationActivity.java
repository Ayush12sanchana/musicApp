package com.example.musicclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicclient.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    EditText fullName,email,password,mobileNumber;
    Button registerBtn;
    TextView backToLogin;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mFirebaseAuth;
    User user;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mobileNumber = findViewById(R.id.phoneNum);
        registerBtn = findViewById(R.id.btn_register);
        backToLogin = findViewById(R.id.login_screen);

        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        user = new User();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(fullName.getText().toString())){
                    fullName.setError("name required");
                    fullName.requestFocus();
                }
                else if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("age required");
                    email.requestFocus();
                }
                else if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("age required");
                    password.requestFocus();
                }
                else if(TextUtils.isEmpty(mobileNumber.getText().toString())){
                    mobileNumber.setError("age required");
                    mobileNumber.requestFocus();
                }

                else if(!(fullName.getText().toString().isEmpty() && email.getText().toString().isEmpty() && password.getText().toString().isEmpty() && mobileNumber.getText().toString().isEmpty())) {

//////////////////////

                    mFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            userId = mFirebaseAuth.getCurrentUser().getUid();

                            user.setName(fullName.getText().toString());
                            user.setEmail(email.getText().toString());
                            user.setPassword(password.getText().toString());
                            user.setMobileNumber(mobileNumber.getText().toString());
                            user.setID(userId);


                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    // inside the method of on Data change we are setting
                                    // our object class to our database reference.
                                    // data base reference will sends data to firebase.
                                    databaseReference.setValue(user);

                                    // after adding this data we are showing toast message.
                                    Toast.makeText(RegistrationActivity.this, "data added", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    // if the data is not added or it is cancelled then
                                    // we are displaying a failure toast message.
                                    Toast.makeText(RegistrationActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                }
            }
        });


        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
