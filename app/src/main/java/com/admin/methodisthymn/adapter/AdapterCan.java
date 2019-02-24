package com.admin.methodisthymn.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.admin.methodisthymn.R;
import com.admin.methodisthymn.model.ItemCan;

import java.util.ArrayList;

public class AdapterCan extends ArrayAdapter<ItemCan> {

    private Activity activity;
    int id;
    ArrayList<ItemCan> itemC;

    public AdapterCan( Activity context, int resource, ArrayList<ItemCan> objects) {

        super(context, resource, objects);
        this.activity=context;
        this.id=resource;
        this.itemC=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if (convertView==null){
            LayoutInflater inflater=activity.getLayoutInflater();
            convertView=inflater.inflate(id,null);
        }

        ItemCan item = itemC.get(position);
        TextView tvCanId = (TextView) convertView.findViewById(R.id.tvCanId);
        TextView tvCanticle = (TextView) convertView.findViewById(R.id.tvCanticle);
        TextView tvSummary = (TextView) convertView.findViewById(R.id.tvSummary);


        tvCanId.setText("C"+item.getId());
        tvCanticle.setText(item.getCanticle());
        tvSummary.setText(item.getSummary());
        return convertView;

    }

}
