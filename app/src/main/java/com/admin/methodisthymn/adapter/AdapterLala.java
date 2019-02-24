package com.admin.methodisthymn.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.admin.methodisthymn.R;
import com.admin.methodisthymn.model.ItemLala;

import java.util.ArrayList;

public class AdapterLala extends ArrayAdapter<ItemLala> {
    private Activity activity;
    int id;
    ArrayList<ItemLala> itemL;

    public AdapterLala( Activity context, int resource, ArrayList<ItemLala> objects)
    {
        super(context, resource, objects);
        this.activity=context;
        this.id=resource;
        this.itemL=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if (convertView==null){
            LayoutInflater inflater=activity.getLayoutInflater();
            convertView=inflater.inflate(id,null);
        }

        ItemLala item = itemL.get(position);
        TextView tvLaId = (TextView) convertView.findViewById(R.id.tvLaId);
        TextView tvLala = (TextView) convertView.findViewById(R.id.tvLala);
        TextView tvStanza = (TextView) convertView.findViewById(R.id.tvStanza);
        TextView tvSummary = (TextView) convertView.findViewById(R.id.tvSummary);


        tvLaId.setText(item.getId());
        tvLala.setText(item.getLala());
        tvStanza.setText(item.getStanza());
        tvSummary.setText(item.getSummary());
        return convertView;

    }

}
