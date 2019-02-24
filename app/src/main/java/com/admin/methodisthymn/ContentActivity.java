package com.admin.methodisthymn;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.admin.methodisthymn.model.Item;

import static com.admin.methodisthymn.MainActivity.KEY_CONTENT;


public class ContentActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String content = "";
        String action = "";

        Intent intent = getIntent();
        if(null != intent){
            content = intent.getStringExtra(KEY_CONTENT);
            action = intent.getStringExtra("actionBarTitle");
        }

        TextView textView = (TextView) findViewById(R.id.hymns_content);
        textView.setText(content);

        getSupportActionBar().setTitle("Hymn - "+action);
    }
}
