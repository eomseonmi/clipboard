package com.example.eomseonmi.clipboard_txt;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,AddFragment.OnFragmentInteractionListener {

    FragmentManager fmng;
    FragmentTransaction ftrans;
    AddFragment addFrg;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    TextView txt4;
    TextView txt3;

    LinearLayout notiPannel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initComponents();
    }

    private void initComponents(){
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        txt3 = findViewById(R.id.txt3);

        initTextView();

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        notiPannel = (LinearLayout) findViewById(R.id.layout);

        addFrg = new AddFragment();

    }
    private void initTextView() {
        txt3.setText("가나다라다");
    }

    private void setFrag(int type){
        notiPannel.setVisibility(View.GONE);
        fmng = getFragmentManager();
        ftrans = fmng.beginTransaction();
        switch (type){
            case 0:
                ftrans.replace(R.id.main_frame, addFrg);
                ftrans.commit();
                break;
            case 1:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1 :
                clipboard((getString(R.string.account1)));
                break;
            case R.id.btn2:
                clipboard((getString(R.string.account2)));
                break;
            case R.id.btn3:
                clipboard(txt3.getText().toString());
                break;
            case R.id.btn4:
                break;
        }
    }

    private void clipboard(String str) {
        ClipboardManager clipboard = (android.content.ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = android.content.ClipData.newPlainText("복사 : ", str);
        clipboard.setPrimaryClip(clip);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add) {
            // Handle the camera action
            setFrag(0);
        } else if (id == R.id.statics) {
            //setFrag(1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
