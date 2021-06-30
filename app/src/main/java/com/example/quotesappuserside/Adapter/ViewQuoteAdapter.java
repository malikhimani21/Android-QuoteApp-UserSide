package com.example.quotesappuserside.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.quotesappuserside.Model.YourQuotesModel;
import com.example.quotesappuserside.R;

import java.util.List;

public class ViewQuoteAdapter extends ArrayAdapter {


    Activity activity;
    List<YourQuotesModel> list;

    public ViewQuoteAdapter(Activity activity, List<YourQuotesModel> list) {
        super(activity, R.layout.layout_your_view_quotes, list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.layout_your_view_quotes, null, true);

//        TextView name = (TextView) convertView.findViewById(R.id.viewquotes_name);
//        TextView email = (TextView) convertView.findViewById(R.id.viewquotes_email);
//        TextView contact = (TextView) convertView.findViewById(R.id.viewquotes_contact);
        TextView quotes = (TextView) convertView.findViewById(R.id.viewquotes_quotes);

        YourQuotesModel yourQuotesModel = list.get(position);

//        name.setText(yourQuotesModel.getName());
//        email.setText(yourQuotesModel.getEmail());
//        contact.setText(yourQuotesModel.getContact());
        quotes.setText(yourQuotesModel.getQuotes());

        return convertView;
    }
}
