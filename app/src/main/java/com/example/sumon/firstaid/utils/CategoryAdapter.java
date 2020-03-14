package com.example.sumon.firstaid.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sumon.firstaid.R;
import com.example.sumon.firstaid.models.IdName;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<IdName> {

    public CategoryAdapter(Context context, ArrayList<IdName> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        IdName idName = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_list_item, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.textView);
        // Populate the data into the template view using the data object
        name.setText(idName.getName());
        // Return the completed view to render on screen
        return convertView;
    }

}
