package com.example.boytsov.foodbasketapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by Boytsov on 10.08.2015.
 */
public class MyCustomAdapter extends ArrayAdapter {
    public MyCustomAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
