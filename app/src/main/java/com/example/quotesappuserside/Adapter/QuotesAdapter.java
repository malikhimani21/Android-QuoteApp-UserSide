package com.example.quotesappuserside.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.quotesappuserside.Model.QuotesModel;
import com.example.quotesappuserside.R;

import java.util.List;

public class QuotesAdapter extends ArrayAdapter {

    Activity activity;
    List<QuotesModel> list;

    public QuotesAdapter(Activity activity, List<QuotesModel> list) {
        super(activity, R.layout.layout_v_motivational, list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.layout_v_motivational, null, true);
        
        TextView line1 = (TextView) convertView.findViewById(R.id.v_motivational);

        QuotesModel quotesModel = list.get(position);

        line1.setText(quotesModel.getQuote());

        return convertView;
    }
}
