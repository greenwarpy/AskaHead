package com.gmail.greenwarpy.askahead;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.Random;

/**
 * Selects a random abilities from those listed in UrzaAbilities.xml
 */
public class AbilityGenerator {

    //preference manager object to handle persistent settings
    private MyPreferenceManager settings;
    //array containing all possible abilities [set][plus/minus/ult][individual abilities]
    private String[][][] abilities;
    //random number generator for ability selection
    private Random rng = new Random();

    public static final String emptyString = "EMPTYARRAY";


    public AbilityGenerator(Context context){
        settings = new MyPreferenceManager(context);
        abilities = getAbilities(context.getResources());
    }


    //assembles a multi dimensional array of abilities
    // organized by [set][plus/minus/ult][specific ability]
    //adapted from code from Ted Hopp posted on https://stackoverflow.com/questions/4326037/android-resource-array-of-arrays
    private String[][][] getAbilities(Resources res){

        //this is a TypedArray of TypedArrays of String arrays
        TypedArray ta1 = res.obtainTypedArray(R.array.abilities);

        int n = ta1.length();
        //The array of abilities being constructed
        String[][][] array = new String[n][][];

        //loop through sets
        for(int i = 0; i < n; i++){
            //id for set array i
            int id1 = ta1.getResourceId(i,0);
            if (id1 > 0) {
                //TypedArray of plus, minus and ult arrays for set i;
                TypedArray ta2 = res.obtainTypedArray(id1);
                //m should always be 3
                int m = ta2.length();
                array[i]= new String[m][];
                //loop through ability sets
                for(int j = 0; j <m; j++){
                    //id for ability array j of set i
                    int id2 = ta2.getResourceId(j, 0);
                    if (id2 > 0) {
                        //add abilities from set i of type j to the array
                        array[i][j] = res.getStringArray(id2);
                    } else {
                        // something wrong with the XML
                    }
                }
                ta2.recycle();
            } else {
                // something wrong with the XML
            }
        }
        ta1.recycle();
        return array;
    }

    //generates a list of abilities from the choice selected and the sets enabled, then randomly selects and displays one
    public String chooseAbility(int choice){

        String [] possibleAbilities =  new String[0];

        //build an array containing strings from selected sets for the option chosen
        for(int i = 0; i < abilities.length; i++){
            //if setting for set i is enabled, add relevant abilities to array
            if(settings.getBool("set" + i + "type" + choice + "enabled")){
                possibleAbilities = CustomFunction.concat(possibleAbilities, abilities[i][choice]);
            }
        }

        if (possibleAbilities.length == 0){
            //if there are no abilities are in the array, return a default message
            return emptyString;


        }else{
            //randomly choose an ability from the array
            String ability = possibleAbilities[rng.nextInt(possibleAbilities.length)];
            return ability;

        }
    }


}
