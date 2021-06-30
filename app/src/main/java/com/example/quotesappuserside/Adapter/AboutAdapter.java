package com.example.quotesappuserside.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.quotesappuserside.Model.AboutModel;
import com.example.quotesappuserside.R;

import java.util.List;

public class AboutAdapter extends ArrayAdapter {


    Activity activity;
    List<AboutModel> list;

    public AboutAdapter(Activity activity, List<AboutModel> list) {
        super(activity, R.layout.layout_aboutus, list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.layout_aboutus, null, true);
        
        TextView line1 = (TextView) convertView.findViewById(R.id.viewAbout_line1);
        TextView line2 = (TextView) convertView.findViewById(R.id.viewAbout_line2);
        TextView line3 = (TextView) convertView.findViewById(R.id.viewAbout_line3);
        TextView line4 = (TextView) convertView.findViewById(R.id.viewAbout_line4);
        TextView line5 = (TextView) convertView.findViewById(R.id.viewAbout_line5);

        AboutModel aboutModel = list.get(position);
      
        line1.setText(aboutModel.getLine1());
        line2.setText(aboutModel.getLine2());
        line3.setText(aboutModel.getLine3());
        line4.setText(aboutModel.getLine4());
        line5.setText(aboutModel.getLine5());

        return convertView;
    }
}
