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

import com.example.quotesappuserside.Model.YourQuotesModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertYourQuotes extends AppCompatActivity {

    private EditText name, email, contact, quotes;
    private Button buttonInsertQuotes;
    private ProgressBar progressBar;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_your_quotes);

        name = findViewById(R.id.your_quotes_name);
        email = findViewById(R.id.your_quotes_email);
        contact = findViewById(R.id.your_quotes_contact);
        quotes = findViewById(R.id.your_quotes_insert_quotes);
        buttonInsertQuotes = findViewById(R.id.your_quotes_button);

        progressBar = findViewById(R.id.your_quotes_ProgressBar);

        databaseReference = FirebaseDatabase.getInstance().getReference("yourQuotes");

        buttonInsertQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String NAME = name.getText().toString().trim();
                final String EMAIL = email.getText().toString().trim();
                final String CONTACT = contact.getText().toString().trim();
                final String YQ = quotes.getText().toString().trim();

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

                if (YQ.isEmpty()) {
                    quotes.setError("Please enter something");
                    quotes.requestFocus();
                    return;
                }


                buttonInsertQuotes.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                String ID = databaseReference.push().getKey();

                YourQuotesModel yourQuotesModel = new YourQuotesModel(ID, NAME, EMAIL, CONTACT, YQ);

                databaseReference.child(ID).setValue(yourQuotesModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(InsertYourQuotes.this, "Successfully Inserted ", Toast.LENGTH_SHORT).show();
                            buttonInsertQuotes.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(InsertYourQuotes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                buttonInsertQuotes.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
            }
        });

    }

    public void openViewQuotesAct(View view) {
        startActivity(new Intent(InsertYourQuotes.this, ViewYourQuotes.class));
    }
}
