package com.gmail.greenwarpy.askahead;

import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Date;

//log for Urza ability activations
public class TextLogDisplay {

    private TextView textBox;
    private String log;

    final private String[] abilTypes = {"+1", "-1", "-6"};

    public TextLogDisplay(){
        Date d = new Date();
        log ="Log started at " + DateFormat.format("hh:mm:ss", d.getTime()) + "\nThe results of previous activations are listed here just in case you need a reminder.";
    }

    //allow user to clear the log
    //intended for when a user starts another game of magic and the log for the prior game is no longer needed
    public void clearLog(){
        Date d = new Date();
        log ="Log cleared at " + DateFormat.format("hh:mm:ss", d.getTime());

        display();
    }

    public void appendActivation(int type,String addition){
        Date d = new Date();
        log = log + "\n\n" +  DateFormat.format("hh:mm:ss", d.getTime()) + "\n" +abilTypes[type] +": " + addition;

        display();
    }

    public void display(){
        if(textBox != null) {
            textBox.setText(log);
        }
    }

    public void setDisplay(TextView textView){
        textBox = textView;
        display();
    }




}
