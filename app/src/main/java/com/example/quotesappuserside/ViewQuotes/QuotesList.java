package com.example.quotesappuserside.ViewQuotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quotesappuserside.R;


public class QuotesList extends AppCompatActivity {

    private ListView listView;
    ArrayAdapter arrayAdapter;
    String string[] = {"VMotivational", "VInspirational", "VSuccess", "VPositive", "VLeadership", "VLife", "VLove", "VAttitude", "VChange", "VPatience", "VPeace", "VEducation", "VRelationship", "VFailure", "VFaith", "VPower", "VFrienship", "VHappiness", "VHealth", "VTrust"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_list);

        listView = findViewById(R.id.quotesAllListView);
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, string);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String pos = string[position];
                try {
                    Class cls = Class.forName("com.example.quotesapp.ViewQuotes." + pos);
                    Intent intent = new Intent(QuotesList.this, cls);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
