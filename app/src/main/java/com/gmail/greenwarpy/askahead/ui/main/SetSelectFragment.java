package com.gmail.greenwarpy.askahead.ui.main;


import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.greenwarpy.askahead.MyPreferenceManager;
import com.gmail.greenwarpy.askahead.R;
import com.gmail.greenwarpy.askahead.SettingsActivity;

/**
 * Fragment responsible for allowing users to choose which sets of abilities.
 * Saves using preferences
 */
public class SetSelectFragment extends Fragment implements Toolbar.OnMenuItemClickListener {

    private SetSelectViewModel mViewModel;
    private Toolbar mActionBarToolbar;

    private MyPreferenceManager myPreferenceManager;
    //Checkboxes
    private CheckBox[] boxes;
    //Background squares for checkboxes
    private ImageView[] backs;
    private int[] imageID;

    private ConstraintLayout[] setLayouts;

    public static SetSelectFragment newInstance() {
        return new SetSelectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.set_select_fragment, container, false);

        int templateID = R.layout.set_select_template;
        int sets = 2;
        setLayouts = new ConstraintLayout[sets];
        imageID = new int[sets];
        for(int i = 0; i < sets ;i++){
            imageID[i]=getResources().getIdentifier("ic_setsymbol"+i, "drawable", getContext().getPackageName());
        }

        for(int i = 0; i <setLayouts.length;i++){
            setLayouts[i] = (ConstraintLayout)inflater.inflate(templateID, null);
        }

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linearLayout = view.findViewById(R.id.settingsListLinearLayout);
        for(int i = 0; i <setLayouts.length;i++){
            TextView setName = setLayouts[i].findViewById(R.id.templateSetName);
            setName.setText(getResources().getStringArray(R.array.sets)[i]);
            ImageView symbol = setLayouts[i].findViewById(R.id.templateSymbol);
            symbol.setImageResource(imageID[i]);

            linearLayout.addView(setLayouts[i], i);
        }

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

        //initialise preferenceManager and checkbox arrays
        myPreferenceManager = new MyPreferenceManager(getContext());

        /*boxes = new CheckBox[]{getActivity().findViewById(R.id.setCheckBox0),getActivity().findViewById(R.id.setCheckBox1)};
        backs = new ImageView[]{getActivity().findViewById(R.id.setBackImage0),getActivity().findViewById(R.id.setBackImage1)};

        //set up each checkbox
        for(int i = 0; i <boxes.length;i++){
            final String key = "set"+ i + "enabled";
            final CheckBox box = boxes[i];
            final ImageView back = backs[i];

            //set checkbox to bool saved in preferences, and set back square to match
            box.setChecked(myPreferenceManager.getBool(key));
            if(box.isChecked()) {
                back.setVisibility(View.VISIBLE);
            }else{
                back.setVisibility(View.INVISIBLE);
            }

            //set up listener for checkboxes
            boxes[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Update preference for set
                    myPreferenceManager.setBool(key,box.isChecked());
                    //change back to visible when box is checked
                    if(box.isChecked()) {
                        back.setVisibility(View.VISIBLE);
                    }else{
                        back.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }


        //back button sends user to middle fragment
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // back button pressed
                ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
                viewPager.setCurrentItem(1);

            }
        });*/

    }

    //opens settings activity
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
        return true;
    }


}
