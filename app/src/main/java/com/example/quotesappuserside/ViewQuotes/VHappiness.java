package com.example.quotesappuserside.ViewQuotes;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.quotesappuserside.Adapter.QuotesAdapter;
import com.example.quotesappuserside.Model.QuotesModel;
import com.example.quotesappuserside.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VHappiness extends AppCompatActivity {

    private ListView listView;
    private Query databaseReference;
    private List<QuotesModel> list;
    private QuotesAdapter quotesAdapter;
    private QuotesModel quotesModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vhappiness);

        listView = (ListView) findViewById(R.id.v_listview_happiness);
        list = new ArrayList<>();

        progressBar = findViewById(R.id.pro_happiness);
        progressBar.setVisibility(View.VISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference("quotes").orderByChild("qt_cat").equalTo("Happiness");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    quotesModel = snap.getValue(QuotesModel.class);
                    list.add(quotesModel);
                }
                quotesAdapter = new QuotesAdapter(VHappiness.this, list);
                listView.setAdapter(quotesAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
