package com.gmail.greenwarpy.askahead;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferenceManager {

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor preferenceEditor;

    public MyPreferenceManager(Context context){
        sharedpreferences = context.getSharedPreferences("SaveFile", Context.MODE_PRIVATE);
        preferenceEditor = sharedpreferences.edit();
        preferenceEditor.apply();
    }

    public MyPreferenceManager(SharedPreferences prefs){
        sharedpreferences = prefs;
        preferenceEditor = sharedpreferences.edit();
        preferenceEditor.apply();
    }

    public boolean getBool(String key){
        return sharedpreferences.getBoolean(key, false);
    }

    public void setBool(String key, boolean bool){
        preferenceEditor.putBoolean(key, bool);
        preferenceEditor.commit();
    }

}
