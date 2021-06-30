package com.example.quotesappuserside.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.quotesappuserside.Model.ModelUser;
import com.example.quotesappuserside.R;

import java.util.List;

public class ProfileAdapter extends ArrayAdapter<ModelUser> {


    Activity activity;
    List<ModelUser> list;

    public ProfileAdapter(Activity activity, List<ModelUser> list) {
        super(activity, R.layout.layout_profile, list);
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_profile, null, true);

        TextView name = (TextView) view.findViewById(R.id.profile_name);
        TextView email = (TextView) view.findViewById(R.id.profile_email);
        TextView contact = (TextView) view.findViewById(R.id.profile_contact);
        TextView username = (TextView) view.findViewById(R.id.profile_username);
        TextView password = (TextView) view.findViewById(R.id.profile_password);

        ModelUser modelUser = list.get(position);

        name.setText(modelUser.getName());
        email.setText(modelUser.getEmail());
        contact.setText(modelUser.getContact());
        username.setText(modelUser.getUsername());
        password.setText(modelUser.getPassword());

        return view;
    }
}


