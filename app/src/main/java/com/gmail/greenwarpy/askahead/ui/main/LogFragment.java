package com.gmail.greenwarpy.askahead.ui.main;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.greenwarpy.askahead.R;
import com.gmail.greenwarpy.askahead.TextLogDisplay;

/**
 * Fragment for displaying and resetting an Ability log
 */
public class LogFragment extends Fragment {

    private LogViewModel mViewModel;
    private Toolbar mActionBarToolbar;
    private TextLogDisplay log;
    private TextView output;

    public static LogFragment newInstance() {
        return new LogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.log_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(LogViewModel.class);

        //fetch log from mViewModel
        log = mViewModel.getLog();
        output = getActivity().findViewById(R.id.logTextView);
        log.setDisplay(output);

        //set up toolbar
        mActionBarToolbar = getActivity().findViewById(R.id.logToolbar);
        setHasOptionsMenu(false);
        mActionBarToolbar.setTitle("Ability Log");

        //set up listener for clear button
        getActivity().findViewById(R.id.clearLogButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log.clearLog();
                log.setDisplay(output);
            }
        });

        //back button sends user to middle fragment
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
                viewPager.setCurrentItem(1);

            }
        });
    }
}
