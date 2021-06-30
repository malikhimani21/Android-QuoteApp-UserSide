package com.example.quotesappuserside;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RateApp extends AppCompatActivity {

    private Button buttonRateApp;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);

        buttonRateApp = findViewById(R.id.buttonRateApp);
        buttonRateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Uri uri = Uri.parse("market://details?id=com.W3school.Anbu");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
//                    startActivity(new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("http://play.google.com/store/apps/details?id=com.W3school.Anbu" + context.getPackageName())));
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=com.W3school.Anbu")));
                }

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(RateApp.this, HomeActivity.class));
    }
}
