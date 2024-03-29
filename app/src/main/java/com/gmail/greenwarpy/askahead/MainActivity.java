package com.gmail.greenwarpy.askahead;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

import com.gmail.greenwarpy.askahead.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        //set initial fragment to askFragment
        viewPager.setCurrentItem(1);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return false;
    }

    @Override
    public void onBackPressed()
    {
        if(viewPager.getCurrentItem() != 1){
            viewPager.setCurrentItem(1);
        }else {
            super.onBackPressed();  // optional depending on your needs
        }
    }

}