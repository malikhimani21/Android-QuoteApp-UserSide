package com.example.quotesappuserside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.quotesappuserside.Model.ModelUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, name, email, contact, password;
    private Button registerButton;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.register_username);
        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        contact = findViewById(R.id.register_contact);
        password = findViewById(R.id.register_password);
        registerButton = findViewById(R.id.buttonRegister);

        progressBar = findViewById(R.id.registeProgressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String USERNAME = username.getText().toString().trim();
                final String NAME = name.getText().toString().trim();
                final String EMAIL = email.getText().toString().trim();
                final String CONTACT = contact.getText().toString().trim();
                final String PASSWORD = password.getText().toString().trim();

                if (USERNAME.isEmpty()) {
                    username.setError("Please enter username");
                    username.requestFocus();
                    return;
                }

                if (NAME.isEmpty()) {
                    name.setError("Please enter name");
                    name.requestFocus();
                    return;
                }

                if (EMAIL.isEmpty()) {
                    email.setError("Please enter email");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(EMAIL).matches()) {
                    email.setError("Email is not valid");
                    email.requestFocus();
                    return;
                }

                if (CONTACT.isEmpty()) {
                    contact.setError("Please enter contact");
                    contact.requestFocus();
                    return;
                }

                if (PASSWORD.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                    return;
                }

                if (PASSWORD.length() < 6) {
                    password.setError("Minimum length of password is 6");
                    password.requestFocus();
                    return;
                }

                registerButton.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        String id = databaseReference.push().getKey();
                        ModelUser modelUser = new ModelUser(id, USERNAME, NAME, EMAIL, CONTACT, PASSWORD);
                        databaseReference.child(id).setValue(modelUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    registerButton.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                }

                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        registerButton.setVisibility(View.VISIBLE);
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });


                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                registerButton.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        });


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getUid() != null) {
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void openLoginActivity(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
