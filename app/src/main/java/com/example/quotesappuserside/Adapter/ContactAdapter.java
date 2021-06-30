package com.example.quotesappuserside.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.quotesappuserside.Model.ContactModel;
import com.example.quotesappuserside.R;

import java.util.List;

public class ContactAdapter extends ArrayAdapter {


    Activity activity;
    List<ContactModel> list;

    public ContactAdapter(Activity activity, List<ContactModel> list) {
        super(activity, R.layout.layout_contactus, list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.layout_contactus, null, true);
        
        TextView num1 = (TextView) convertView.findViewById(R.id.viewcontact_num1);
        TextView num2 = (TextView) convertView.findViewById(R.id.viewcontact_num2);
        TextView email = (TextView) convertView.findViewById(R.id.viewcontact_email);
        TextView website = (TextView) convertView.findViewById(R.id.viewcontact_website);
        TextView address = (TextView) convertView.findViewById(R.id.viewcontact_address);


        ContactModel contactModel = list.get(position);

      
        num1.setText(contactModel.getNum1());
        num2.setText(contactModel.getNum2());
        email.setText(contactModel.getEmail());
        website.setText(contactModel.getWebsite());
        address.setText(contactModel.getAddress());
        return convertView;
    }
}
