package com.gmail.greenwarpy.askahead.ui.main;

import androidx.lifecycle.ViewModel;

import com.gmail.greenwarpy.askahead.TextLogDisplay;

public class LogViewModel extends ViewModel {

    //object handles displaying the ability log, used by both LogFragment and AskFragment
   private TextLogDisplay log = new TextLogDisplay();

    public TextLogDisplay getLog(){
        return log;
    }

}
