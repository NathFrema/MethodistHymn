package com.admin.methodisthymn;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.admin.methodisthymn.adapter.AdapterCan;
import com.admin.methodisthymn.database.DatabaseHelper;
import com.admin.methodisthymn.fontAdapter.App_Utils;
import com.admin.methodisthymn.model.ItemCan;

import java.sql.SQLException;
import java.util.ArrayList;

public class CanticleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ListView listView;
    private DatabaseHelper dbHelper;
    AdapterCan adapter;
    ArrayList<ItemCan> arrayList=new ArrayList<ItemCan>();
    public static final String KEY_CANTICLE="canticle_content";

    private App_Utils app_utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawerc);

        this.app_utils = new App_Utils(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("GA Canticles");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_canticles);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        dbHelper = new DatabaseHelper(this);
        try{
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        }catch (SQLException e){

        }
        try{
            Cursor cursor=dbHelper.QueryData("select * from tblCanticle");
            if ( cursor !=null){
                if (cursor.moveToFirst()){
                    do {
                        ItemCan item=new ItemCan();
                        item.setId(cursor.getString(0));
                        item.setCanticle(cursor.getString(1));
                        item.setSummary(cursor.getString(2));
                        item.setContents(cursor.getString(3));
                        arrayList.add(item);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLException e){ }
        adapter = new AdapterCan(this,R.layout.canticle_item, arrayList);
        listView = (ListView) findViewById(R.id.lvCanticle);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemCan item = (ItemCan) adapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), CanticleContentActivity.class);
                intent.putExtra(KEY_CANTICLE, item.getContents());
                intent.putExtra("actionTitle", item.getId());

                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_share)
        {

        }else if(id == R.id.action_exit)
        {
            this.app_utils.exit();
            return true;

           *//* AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Ga Methodist Hymn")
                    .setMessage("Do you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CanticleActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });*//*
        }else if(id == R.id.search_text)
        {

        }
*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_methodist) {
            Intent intent = new Intent(CanticleActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_canticles) {
            Intent intent = new Intent(CanticleActivity.this, CanticleActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_holy) {
            Intent intent = new Intent(CanticleActivity.this, LalaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_extras) {
            Intent intent = new Intent(CanticleActivity.this, ExtrasActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_choir) {

        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shareapp_msg)+"\n"+"\n"+"https://play.google.com/store/apps/details?id="+getPackageName());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }  else if (id == R.id.nav_appreciation) {
            this.app_utils.apr();
        } else if (id == R.id.nav_about) {
            this.app_utils.about();
        } else if (id == R.id.nav_contact) {
            this.app_utils.contact();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            //this.app_utils.exit();
            super.onBackPressed();
        }
    }

}
