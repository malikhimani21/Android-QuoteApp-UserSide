package com.example.quotesappuserside;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShareApp extends AppCompatActivity {

    private Button buttonShareApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_app);

        buttonShareApp = findViewById(R.id.buttonShareApp);
        buttonShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Felix ITs System");
                    String shareMessage = "\nLet me recommend you this application\n\n";
//                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.W3school.Anbu=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.W3school.Anbu"+ "\n\n";

                    intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(intent, "choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
