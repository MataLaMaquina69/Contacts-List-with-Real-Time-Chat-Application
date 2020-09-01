package com.mata.contactslistwithrealtimechatapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailTx;
    private EditText passwordTx;
    private EditText  lastNameTx;
    private EditText phoneTx;
    private EditText firstNameTx;
    private Button signUpBtn;

    private TextView signInTx;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        emailTx = findViewById(R.id.email_text);
        passwordTx = findViewById(R.id.password_txt);
        phoneTx = findViewById(R.id.phone_txt);
        lastNameTx = findViewById(R.id.lastName_txt);
        firstNameTx = findViewById(R.id.firstName_txt);

        signUpBtn = findViewById(R.id.createAccount_btn);

        signInTx = findViewById(R.id.singIn_btn);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = emailTx.getText().toString().trim();
                String firstName = firstNameTx.getText().toString().trim();
                String phone = phoneTx.getText().toString().trim();

                String lastName = lastNameTx.getText().toString().trim();

                String mPassword = passwordTx.getText().toString().trim();
                if (TextUtils.isEmpty(mEmail)) {
                    emailTx.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(mPassword)) {
                    emailTx.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(firstName)) {
                    firstNameTx.setError("Required Field...");
                    return;
                }

                if (TextUtils.isEmpty(lastName)) {
                    lastNameTx.setError("Required Field...");
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    phoneTx.setError("Required Field...");
                    return;
                }



                progressDialog.setMessage("Processing");
                progressDialog.dismiss();
                firebaseAuth.createUserWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(),"Somthing went Wrong",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
            }
        });


        signInTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }




}