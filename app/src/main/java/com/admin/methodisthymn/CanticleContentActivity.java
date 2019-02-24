package com.admin.methodisthymn;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import static com.admin.methodisthymn.CanticleActivity.KEY_CANTICLE;


public class CanticleContentActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canticle_content);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       String canticle = "";
       String actionsB = "";

        Intent in = getIntent();
        if(null != in){
            canticle = in.getStringExtra(KEY_CANTICLE);
            actionsB = in.getStringExtra("actionTitle");
        }
        TextView tv = (TextView)findViewById(R.id.canticle_content);
        tv.setText(canticle);
        getSupportActionBar().setTitle("CANTICLES - "+actionsB);
    }
}
