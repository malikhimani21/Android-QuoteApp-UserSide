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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.buttonLogin);

        progressBar = findViewById(R.id.progressbarLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String EMAIl = email.getText().toString().trim();
                String PASS = password.getText().toString().trim();

                if (EMAIl.isEmpty()) {
                    email.setError("Please enter email");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(EMAIl).matches()) {
                    email.setError("Email is not valid");
                    email.requestFocus();
                    return;
                }

                if (PASS.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                    return;
                }

                if (PASS.length() < 6) {
                    password.setError("Minimum length of password is 6");
                    password.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                login.setVisibility(View.GONE);


                firebaseAuth.signInWithEmailAndPassword(EMAIl, PASS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            progressBar.setVisibility(View.GONE);
                            login.setVisibility(View.VISIBLE);

                        }

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                login.setVisibility(View.VISIBLE);

                            }
                        });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getUid() != null) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void openRegisterActivity(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
