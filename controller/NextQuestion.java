package com.example.jstachuralab2.controller;


import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.lang.Math;

public class NextQuestion {
    private static int index = 0;
    private static int total_number_of_questions;
    private static Set<Integer> unanswered;


    public NextQuestion(int total_qs){
        // Initialize object variables, and fill the unanswered set with all of the available indexes.

        total_number_of_questions = total_qs;
        unanswered = new HashSet<Integer>();
        unanswered.clear();
        for(int i = 0; i < total_number_of_questions; i++){
            unanswered.add(Integer.valueOf(i));
        }
    }






    public int getCurrentQuestion(){
        unanswered.remove(Integer.valueOf(index));
        return index;
    }

    public int getNextQuestionIndex(){
        // Get a random question index from the remaining unanswered indexes.

        //If empty, quiz is finished so we return -1 as an exit code.
        if(unanswered.isEmpty()){
            return -1;
        }
        //get array of unanswered indexes
        Integer[] unanswered_array = unanswered.toArray(new Integer[unanswered.size()]);

        //choose a random element as the next index
        Random rand = new Random();
        int rnd = rand.nextInt(unanswered_array.length);
        index = unanswered_array[rnd];
        return index;






    }

}
