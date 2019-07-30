package com.gmail.greenwarpy.askahead.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.greenwarpy.askahead.AbilityGenerator;
import com.gmail.greenwarpy.askahead.ui.main.AskViewModel;
import com.gmail.greenwarpy.askahead.CustomFunction;
import com.gmail.greenwarpy.askahead.ui.main.LogViewModel;
import com.gmail.greenwarpy.askahead.TextLogDisplay;
import com.gmail.greenwarpy.askahead.TextTypingDisplay;
import com.gmail.greenwarpy.askahead.R;

public class AskFragment extends Fragment {

    private AskViewModel mViewModel;
    private LogViewModel logViewModel;

    public static AskFragment newInstance() {
        return new AskFragment();
    }

    private TextTypingDisplay abilityOutput;
    private AbilityGenerator abilityGenerator;
    private TextLogDisplay abilityLog;

    //variables for loyalty counter
    final int defaultLoyalty = 4;
    private int loyaltyCount = defaultLoyalty;
    private TextView loyaltyCounterView;


    private boolean counterEnabled = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ask_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AskViewModel.class);
        logViewModel = ViewModelProviders.of(getActivity()).get(LogViewModel.class);
        // TODO: Use the ViewModel

        TextView textBox = getActivity().findViewById(R.id.abilityOutputTextView);
        abilityOutput = new TextTypingDisplay(textBox);
        abilityGenerator = new AbilityGenerator(getContext());

        abilityLog = logViewModel.getLog();

        //Hide loyalty counter if it is not enabled
        counterEnabled = PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("counterEnabled",false);
        if(!counterEnabled){
            getActivity().findViewById(R.id.loyaltyCounterGroup).setVisibility(View.GONE);
        }
        loyaltyCounterView = getActivity().findViewById(R.id.loyaltyText);

        //Set up listeners for buttons

        //Loyalty ability listeners
        //plus ability listener
        getActivity().findViewById(R.id.abilityPlusButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activation(0);
                if(PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("counterAutomatic",false)){
                    changeLoyalty(+1);
                }
            }
        });

        //minus ability listener
        getActivity().findViewById(R.id.abilityMinusButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activation(1);
                if(PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("counterAutomatic",false)){
                    changeLoyalty(-1);
                }
            }
        });

        //ult ability listener
        getActivity().findViewById(R.id.abilityUltButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activation(2);
                if(PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("counterAutomatic",false)){
                    changeLoyalty(-6);
                }
            }
        });

        //listener for ability output, sends user to log when tapped
        getActivity().findViewById(R.id.abilityOutputTextView).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
                viewPager.setCurrentItem(0);
            }
        });

        //Loyalty counter listeners
        //+ button
        getActivity().findViewById(R.id.loyaltyPlusButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeLoyalty(+1);
            }
        });
        //- button
        getActivity().findViewById(R.id.loyaltyMinusButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeLoyalty(-1);
            }
        });
        //loyalty counter button
        //reset loyalty counter
        getActivity().findViewById(R.id.loyaltyButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setLoyalty(defaultLoyalty);
            }
        });

        getActivity().findViewById(R.id.logTabImage).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
                viewPager.setCurrentItem(0);
            }
        });

        getActivity().findViewById(R.id.setTabImage).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
                viewPager.setCurrentItem(2);
            }
        });

    }

    //function to handle ability activations, fetches ability then displays it
    private void activation(int choice){
        //Generate an ability string
        String ability = abilityGenerator.chooseAbility(choice);

        if(ability == AbilityGenerator.emptyString){
            //if abilityGenerator returns the emptyString, it's most likely because no sets are selected.
            //send user to set_select_fragment
            ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
            viewPager.setCurrentItem(2);
        }else {

            abilityOutput.setText(ability, 1);
            abilityLog.appendActivation(choice, ability);
        }



    }

    private void changeLoyalty(int change){
        setLoyalty(loyaltyCount + change);
    }

    private void setLoyalty(int val){
        loyaltyCount = CustomFunction.bound(0,val,999);
        loyaltyCounterView.setText(""+loyaltyCount);
    }


}
