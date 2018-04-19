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

public class TFAdapter extends ArrayAdapter {
    ArrayList<String> content2 = new ArrayList<>();

    public TFAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
        content2 = (ArrayList<String>) objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.tf_row, null);
        TextView textView = (TextView) v.findViewById(R.id.tf_text);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView_tf);
        textView.setText(content2.get(position));
        return v;
    }
}
