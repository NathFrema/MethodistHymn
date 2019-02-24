package com.admin.methodisthymn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import static com.admin.methodisthymn.LalaActivity.KEY_LALA;

public class LalaContentActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lala_content);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String lala = "";
        String actionsL = "";

        Intent in = getIntent();
        if(null != in){
            lala = in.getStringExtra(KEY_LALA);
            actionsL = in.getStringExtra("actionTitle");
        }

        TextView tv = (TextView)findViewById(R.id.lala_content);
        tv.setText(lala);
        getSupportActionBar().setTitle("LALA - "+actionsL);

    }
}
