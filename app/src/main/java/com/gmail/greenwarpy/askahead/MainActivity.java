package com.gmail.greenwarpy.askahead;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

import com.gmail.greenwarpy.askahead.ui.main.SectionsPagerAdapter;

import java.util.List;

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

    /*//source https://www.skoumal.com/en/android-handle-back-press-in-fragment/
    @Override
    public void onBackPressed() {
        List fragmentList = getSupportFragmentManager().getFragments();

        boolean handled = false;
        for(Fragment f : fragmentList) {
            if(f instanceof BaseFragment) {
                handled = ((BaseFragment)f).onBackPressed();

                if(handled) {
                    break;
                }
            }
        }

        if(!handled) {
            super.onBackPressed();
        }
    }*/

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() != 1)
        {
            viewPager.setCurrentItem(1);
        } else
        {
            finish();
        }
    }

}