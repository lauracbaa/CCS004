package com.example.laura.ccs004;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseFirestore mUsers;
    private Button mBtnSave;
    private EditText mTxtName, mTxtEmail, mTxtPassword, mTxtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mUsers = FirebaseFirestore.getInstance();

        mBtnSave = (Button) findViewById(R.id.btnRegister);
        mTxtName = (EditText) findViewById(R.id.txtName);
        mTxtEmail = (EditText) findViewById(R.id.txtEmail);
        mTxtPassword = (EditText) findViewById(R.id.txtPassword);
        mTxtPhone = (EditText) findViewById(R.id.txtPhone);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mTxtName.getText().toString();
                final String email = mTxtEmail.getText().toString();
                String password = mTxtPassword.getText().toString();
                String phone = mTxtPhone.getText().toString();

                Map<String, String> userMap = new HashMap<>();

                userMap.put("FullName", name);
                userMap.put("Email", email);
                userMap.put("Password", password);
                userMap.put("Telephone", phone);

                mUsers.collection("UsersDB").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {



                        Toast.makeText(RegistrationActivity.this, "Account has been created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        String error = e.getMessage();
                        Toast.makeText(RegistrationActivity.this, "Error: This email address has already been used", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

    }
}
