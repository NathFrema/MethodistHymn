package com.admin.methodisthymn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

public class ExtrasActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView nc, ac, lp, iw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Methodist Extras");

        nc = (TextView)findViewById(R.id.nc);
        ac = (TextView)findViewById(R.id.ac);
        lp = (TextView)findViewById(R.id.lp);
        iw = (TextView)findViewById(R.id.iw);

        nc.setText(R.string.nicen);
        ac.setText(R.string.apostle);
        lp.setText(R.string.prayer);
        iw.setText(R.string.build);
    }
}
