package com.example.oliver.finalproject2018;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minyiyang on 2018-04-11.
 */

public class NumAdapter extends ArrayAdapter {
    ArrayList<String> content1 = new ArrayList<>();

    public NumAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
        content1 = (ArrayList<String>) objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.num_row, null);
        TextView textView = (TextView) v.findViewById(R.id.num_text);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView_num);
        textView.setText(content1.get(position));
        return v;
    }
}
