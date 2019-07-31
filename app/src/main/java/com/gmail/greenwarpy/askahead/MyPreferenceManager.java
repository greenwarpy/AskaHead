package com.gmail.greenwarpy.askahead;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * class to slightly simplify writing to and reading preferences
 */
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

    //get bool for key
    public boolean getBool(String key){
        return sharedpreferences.getBoolean(key, false);
    }

    //get set bool for key
    public void setBool(String key, boolean bool){
        preferenceEditor.putBoolean(key, bool);
        preferenceEditor.commit();
    }

}
