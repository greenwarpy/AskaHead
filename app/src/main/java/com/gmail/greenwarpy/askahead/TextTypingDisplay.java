package com.gmail.greenwarpy.askahead;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 *Class to handle setting text in a textView
 *Letters display in order instead of appearing all at once
 *Reminder text set to italics
 */
public class TextTypingDisplay {

    private Handler handler;
    private TextView textView;
    private int increment = 0;
    private Timer timer;

    private SpannableString textSpanString;
    private final ForegroundColorSpan transparentSpan = new ForegroundColorSpan(0);
    private final StyleSpan italicsSpan = new StyleSpan(Typeface.ITALIC);

    public TextTypingDisplay(TextView textBox){
        textView = textBox;

        handler = new Handler(new Handler.Callback(){
            @Override
            public boolean handleMessage(Message msg) {
                update();
                return true;
            }
        });
    }


    /**
     * Set a new string to display
     * @param newText text to display
     * @param milli delay in millimeters between letters being displayed
     */
    public void setText(String newText, int milli) {
       //cancel timer
        if(timer != null){
           timer.cancel();
       }
        increment = 0;

        //set the spannableString to the text to display
        textSpanString = new SpannableString(newText);

        //set any text after a '(' character to italics
        //in practice this should always be reminder text
        //should be removed if class is reused
        if(newText.lastIndexOf('(') != -1){
            textSpanString.setSpan(italicsSpan,newText.lastIndexOf("("), textSpanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        //set string to a transparent colour
        //doing this instead of omitting them from the string prevents text shunting to the next line as the text box fills
        textSpanString.setSpan(transparentSpan,increment, textSpanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(textSpanString);



        //set up a new timer to tell handler to call update() every milli milliseconds
        timer = new Timer();
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if(increment >= textSpanString.length()){
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask,1,milli);


    }

    //increase the increment and show one more letter
    private void update(){
        if(increment++ < textSpanString.length()){
            //change the transparent span to include 1 less letter
            textSpanString.setSpan(transparentSpan,increment, textSpanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //display the SpannableString
            textView.setText(textSpanString);
        }
    }


}
