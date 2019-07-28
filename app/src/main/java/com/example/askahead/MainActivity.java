package com.example.askahead;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.askahead.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        //set initial page to 1 (askUrza screen)
        viewPager.setCurrentItem(1);

        //mActionBarToolbar = (Toolbar) findViewById(R.id.setSelectToolbar);
        //mActionBarToolbar.setVisibility(View.GONE);
        //setSupportActionBar(mActionBarToolbar);

        //getSupportActionBar().setTitle("My title");

        //getSupportActionBar().setTitle("My title");

        /*TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);*/

        /*FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return false;
    }


}