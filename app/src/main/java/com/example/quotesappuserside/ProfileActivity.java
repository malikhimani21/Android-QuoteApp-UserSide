package com.example.quotesappuserside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.quotesappuserside.Adapter.ProfileAdapter;
import com.example.quotesappuserside.Model.ModelUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ListView listView;
    private Query databaseReference;
    private List<ModelUser> list;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        progressBar = findViewById(R.id.profile_progressBar);
        listView = (ListView) findViewById(R.id.profile_listView);

        // for fetching all students
        //databaseReference = FirebaseDatabase.getInstance().getReference("user");
        databaseReference = FirebaseDatabase.getInstance().getReference("user").orderByChild("email").equalTo(FirebaseAuth.getInstance().getCurrentUser().getEmail());


        //databaseReference = FirebaseDatabase.getInstance().getReference("student" + FirebaseAuth.getInstance().getCurrentUser().getUid());
        list = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
//        databaseReference.orderByChild("email").equalTo(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addValueEventListener(new ValueEventListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    ModelUser modelRegister = snap.getValue(ModelUser.class);
                    list.add(modelRegister);
                }
                ProfileAdapter profileAdapter = new ProfileAdapter(ProfileActivity.this, list);
                listView.setAdapter(profileAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
