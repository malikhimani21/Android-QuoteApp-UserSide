package com.example.quotesappuserside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.quotesappuserside.Model.ModelInquiry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InquiryActivity extends AppCompatActivity {

    private EditText name, email, contact, askAnything, feedback;
    private Button inquiryButton;
    private ProgressBar progressBar;

    private MediaPlayer mediaPlayer;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

        name = findViewById(R.id.inquiry_name);
        email = findViewById(R.id.inquiry_email);
        contact = findViewById(R.id.inquiry_contact);
        askAnything = findViewById(R.id.inquiry_askanything);
        feedback = findViewById(R.id.inquiry_feedback);
        inquiryButton = findViewById(R.id.inquiry_button);

        progressBar = findViewById(R.id.inquiry_ProgressBar);

        mediaPlayer = MediaPlayer.create(InquiryActivity.this, R.raw.splash_tone);

        databaseReference = FirebaseDatabase.getInstance().getReference("inquiry");

        inquiryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String NAME = name.getText().toString().trim();
                final String EMAIL = email.getText().toString().trim();
                final String CONTACT = contact.getText().toString().trim();
                final String ASK = askAnything.getText().toString().trim();
                final String FEEDBACK = feedback.getText().toString().trim();

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

                if (ASK.isEmpty()) {
                    askAnything.setError("Please enter query");
                    askAnything.requestFocus();
                    return;
                }

                if (FEEDBACK.isEmpty()) {
                    feedback.setError("Please enter feedback");
                    feedback.requestFocus();
                    return;
                }

                inquiryButton.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                String ID = databaseReference.push().getKey();

                ModelInquiry modelInquiry = new ModelInquiry(ID, NAME, EMAIL, CONTACT, ASK, FEEDBACK);

                databaseReference.child(ID).setValue(modelInquiry).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mediaPlayer.start();
                            Toast.makeText(InquiryActivity.this, "Query Submited Successfully", Toast.LENGTH_SHORT).show();
                            inquiryButton.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);

                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        name.setText("");
                                        email.setText("");
                                        contact.setText("");
                                        askAnything.setText("");
                                        feedback.setText("");
                                        sleep(3000);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } finally {
                                        Intent intent = new Intent(InquiryActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                        mediaPlayer.stop();
                                    }
                                }
                            };
                            thread.start();

                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(InquiryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                inquiryButton.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        });

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
