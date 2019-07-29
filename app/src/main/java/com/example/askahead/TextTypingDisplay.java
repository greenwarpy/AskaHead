package com.example.askahead;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TextTypingDisplay {

    private Handler handler;
    private Runnable r;
    private TextView textView;
    private String text;
    private int increment = 0;
    private int timeMilli = 1000;
    private Timer timer;

    public TextTypingDisplay(TextView textBox){
        textView = textBox;


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                update();
            }
        };


    }



    public void setText(String newText, int milli) {
       if(timer != null){
           timer.cancel();
       }

        text = newText;
        increment = 0;
        //timeMilli = milli;
        //update();

        timer = new Timer();
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if(increment >= text.length()){
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask,1,milli);

    }

    private void update(){
        if(increment++ < text.length()){
            String subString = text.substring(0,increment);
            textView.setText(subString);
        }
    }


}
