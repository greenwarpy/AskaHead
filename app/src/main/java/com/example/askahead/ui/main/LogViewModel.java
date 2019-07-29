package com.example.askahead.ui.main;

import androidx.lifecycle.ViewModel;

import com.example.askahead.TextLogDisplay;

public class LogViewModel extends ViewModel {

   private TextLogDisplay log = new TextLogDisplay();

    public TextLogDisplay getLog(){
        return log;
    }

    // TODO: Implement the ViewModel
}
