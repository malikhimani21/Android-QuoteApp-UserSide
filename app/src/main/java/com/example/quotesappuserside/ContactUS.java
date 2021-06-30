package com.example.quotesappuserside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.quotesappuserside.Adapter.ContactAdapter;
import com.example.quotesappuserside.Model.ContactModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContactUS extends AppCompatActivity {

    private ListView listView;
    private DatabaseReference databaseReference;
    private List<ContactModel> list;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        listView = (ListView) findViewById(R.id.listViewContact);
        list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("contactus");

        progressBar = findViewById(R.id.pro_activity_contact_us);

        imageView = (ImageView) findViewById(R.id.contact_image);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/quotesapp-b2e91.appspot.com/o/contactus%2Fcontactus_image.jpeg?alt=media&token=4517edb6-3c95-42a6-8f3c-72a922279cdf").into(imageView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    ContactModel contactModel = snap.getValue(ContactModel.class);
                    list.add(contactModel);
                }
                ContactAdapter contactAdapter = new ContactAdapter(ContactUS.this, list);
                listView.setAdapter(contactAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
