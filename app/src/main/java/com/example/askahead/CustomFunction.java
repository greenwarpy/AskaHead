package com.example.askahead;


import java.util.Arrays;

public class CustomFunction {

    //From Joachim Sauer on https://stackoverflow.com/questions/80476/how-can-i-concatenate-two-arrays-in-java
    //concatenates 2 arrays
    public static <T> T[] concat(T[] first, T[] second) {

        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }




}
