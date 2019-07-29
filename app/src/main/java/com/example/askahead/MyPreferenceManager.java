package com.example.askahead;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferenceManager {

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor preferenceEditor;

    public MyPreferenceManager(Context context){
        sharedpreferences = context.getSharedPreferences("SaveFile", Context.MODE_PRIVATE);
        preferenceEditor = sharedpreferences.edit();
    }

    public MyPreferenceManager(SharedPreferences prefs){
        sharedpreferences = prefs;
        preferenceEditor = sharedpreferences.edit();
    }

    public boolean getBool(String key){
        return sharedpreferences.getBoolean(key, false);
    }

    public void setBool(String key, boolean bool){
        preferenceEditor.putBoolean(key, bool);
        preferenceEditor.commit();
    }

}
