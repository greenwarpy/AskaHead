package com.example.askahead.ui.main;

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

import com.example.askahead.AbilityGenerator;
import com.example.askahead.R;
import com.example.askahead.TextTypingDisplay;

public class AskFragment extends Fragment {

    private AskViewModel mViewModel;

    public static AskFragment newInstance() {
        return new AskFragment();
    }

    private TextTypingDisplay abilityOutput;
    private AbilityGenerator abilityGenerator;

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
        // TODO: Use the ViewModel

        TextView textBox = getActivity().findViewById(R.id.abilityOutputTextView);
        abilityOutput = new TextTypingDisplay(textBox);
        abilityGenerator = new AbilityGenerator(getContext());

        //Hide loyalty counter if it is not enabled
        counterEnabled = PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("counterEnabled",false);
        if(!counterEnabled){
            getActivity().findViewById(R.id.loyaltyCounterGroup).setVisibility(View.GONE);
        }

        //plus ability listener
        getActivity().findViewById(R.id.abilityPlusButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activation(0);
            }
        });

        //minus ability listener
        getActivity().findViewById(R.id.abilityMinusButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activation(1);
            }
        });

        //ult ability listener
        getActivity().findViewById(R.id.abilityUltButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activation(2);
            }
        });

        //listener for ability output, sends user to log when tapped
        getActivity().findViewById(R.id.abilityOutputTextView).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
                viewPager.setCurrentItem(0);
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
            //display ability
            abilityOutput.setText(ability, 1);
        }

    }


}
