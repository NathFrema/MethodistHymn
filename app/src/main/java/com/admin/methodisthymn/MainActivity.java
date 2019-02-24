package com.admin.methodisthymn;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
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
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.admin.methodisthymn.adapter.Adapter;
import com.admin.methodisthymn.database.DatabaseHelper;
import com.admin.methodisthymn.fontAdapter.App_Utils;
import com.admin.methodisthymn.fontAdapter.CustomTypefaceSpan;
import com.admin.methodisthymn.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView t;
    private App_Utils app_utils;

    ListView listView;
    private DatabaseHelper dbHelper;
    Adapter adapter;
    ArrayList<Item> arrayList=new ArrayList<Item>();
    public static final String KEY_CONTENT="hymns_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/hb.ttf",true);

        this.app_utils = new App_Utils(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_methodist);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for applying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }

        dbHelper = new DatabaseHelper(this);
        try{
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        }catch (SQLException e){

        }
        try{
            Cursor cursor=dbHelper.QueryData("select * from tblHymn");
            if ( cursor !=null){
                if (cursor.moveToFirst()){
                    do {
                        Item item=new Item();
                        item.setId(cursor.getString(0));
                        item.setHymn(cursor.getString(1));
                        item.setStanza(cursor.getString(2));
                        item.setContent(cursor.getString(3));
                        arrayList.add(item);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLException e){ }
        adapter = new Adapter(this,R.layout.hymn_item, arrayList);
        listView = (ListView) findViewById(R.id.lvHymn);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) adapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                intent.putExtra(KEY_CONTENT, item.getContent());
                intent.putExtra("actionBarTitle", item.getId());

                startActivity(intent);
            }
        });

    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "font/hb.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    public void setActionBarTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        MenuItem item = menu.findItem(R.id.search_text);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(newText);
                }

                return true;
            }
        });

        return true;
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
            *//*AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Ga Methodist Hymn")
                    .setMessage("Do you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });*//*
        }*/
       /* else if(id == R.id.search_text)
        {

        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_methodist) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_canticles) {
            Intent intent = new Intent(MainActivity.this, CanticleActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_holy) {
            Intent intent = new Intent(MainActivity.this, LalaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_extras) {
            Intent intent = new Intent(MainActivity.this, ExtrasActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_choir) {

        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shareapp_msg)+"\n"+"\n"+"https://play.google.com/store/apps/details?id="+getPackageName());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } else if (id == R.id.nav_appreciation) {
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
