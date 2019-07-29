package com.example.askahead.ui.main;


import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.askahead.R;
import com.example.askahead.SettingsActivity;

public class SetSelectFragment extends Fragment implements Toolbar.OnMenuItemClickListener {

    private SetSelectViewModel mViewModel;
    private Toolbar mActionBarToolbar;

    public static SetSelectFragment newInstance() {
        return new SetSelectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.set_select_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SetSelectViewModel.class);

        //set up toolbar
        mActionBarToolbar = getActivity().findViewById(R.id.setSelectToolbar);
        setHasOptionsMenu(true);
        mActionBarToolbar.setTitle("Set Select");
        mActionBarToolbar.setOnMenuItemClickListener(this);
        mActionBarToolbar.inflateMenu(R.menu.settings_menu);

        //back button sends user to middle fragment
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
                viewPager.setCurrentItem(1);

            }
        });
        // TODO: Use the ViewModel
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
        return true;
    }


}
